<template>
  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

<!--    步骤条-->
    <el-steps :active="1" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

<!--    课程表单-->
    <el-form label-width="120px">

      <el-form-item label="课程标题">
        <el-input v-model="courseInfo.title" placeholder=" 示例：机器学习项目课：从基础到搭建项目视频课程。专业名称注意大小写"/>
      </el-form-item>

      <!-- 所属分类 TODO -->
      <el-form-item label="课程分类">
        <el-select
          v-model="courseInfo.subjectParentId"
          placeholder="一级分类" @change="subjectLevelOneChanged">

          <el-option
            v-for="subject in subjectOneList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>

        </el-select>

        <!-- 二级分类 -->
        <el-select v-model="courseInfo.subjectId" placeholder="二级分类">
          <el-option
            v-for="subject in subjectTwoList"
            :key="subject.id"
            :label="subject.title"
            :value="subject.id"/>
        </el-select>
      </el-form-item>


      <!-- 课程讲师 TODO -->
      <!-- 课程讲师 -->
      <el-form-item label="课程讲师">
        <el-select
          v-model="courseInfo.teacherId"
          placeholder="请选择">

          <el-option
            v-for="teacher in teacherList"
            :key="teacher.id"
            :label="teacher.name"
            :value="teacher.id"/>

        </el-select>
      </el-form-item>

      <el-form-item label="总课时">
        <el-input-number :min="0" v-model="courseInfo.lessonNum" controls-position="right" placeholder="请填写课程的总课时数"/>
      </el-form-item>

      <!-- 课程简介 TODO -->
      <el-form-item label="课程简介">
        <tinymce :height="300" v-model="courseInfo.description"/>
      </el-form-item>


      <!-- 课程封面 TODO -->
      <!-- 课程封面-->
      <el-form-item label="课程封面">

        <el-upload
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :action="BASE_API+'/eduoss/fileoss'"
          class="avatar-uploader">
          <img :src="courseInfo.cover">
        </el-upload>

      </el-form-item>

      <el-form-item label="课程价格">
        <el-input-number :min="0" v-model="courseInfo.price" controls-position="right" placeholder="免费课程请设置为0元"/> 元
      </el-form-item>

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存并下一步</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>

import course from '../../../api/edu/course'
import teacher from '../../../api/edu/teacher'
import subject from '../../../api/edu/subject'
import { myLog } from '../../../utils/myUtile'
import Tinymce from '../../../components/Tinymce'

export default {
  name: 'info',
  components: { Tinymce },
  data() {
    return {
      saveBtnDisabled: false, //是否禁用
      courseId: null, //课程ID
      courseInfo: {
        title: '',
        subjectId: '',//二级分类id
        subjectParentId:'',//一级分类id
        teacherId: '',
        lessonNum: 0,
        description: '',
        cover: '/static/people.jpg',
        price: 0
      }, //课程表单数据
      teacherList: {}, //教师列表
      subjectOneList: {}, //一级分类列表
      subjectTwoList: {}, //二级分类列表
      BASE_API: process.env.BASE_API, // 上传图片接口API地址
    }
  },
  created() {
    this.getTeacherList()
    this.getSubjectList()
    // 判断是添加还是修改
    let courseId = this.$route.params.courseId
    if (courseId != null) {
      this.courseId = courseId
      this.getCourse()
    }
  },
  methods: {
    saveOrUpdate() {
      if (this.courseId != null) {
        this.updateCourse();
      }else {
        this.addCourse();
      }
    },
    //封面上传封面成功调用的方法
    handleAvatarSuccess(res, file) {
      this.courseInfo.cover = res.data.url
    },
    //封面上传之前调用的方法
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      return isJPG
    },
    //分类二级联动方法
    subjectLevelOneChanged(id) {
      this.courseInfo.subjectId = ""
      this.subjectTwoList = this.subjectOneList.filter(o => {
        return o.id == id
      })[0].children
    },
    //根据课程ID查询课程
    getCourse() {
      course.getCourseById(this.courseId)
        .then(res => {
          this.courseInfo = res.data.list
        })
    },
    //修改课程
    updateCourse() {
      course.updateCourse(this.courseInfo)
        .then(res => {
          this.$message({
            type: 'success',
            message: '修改课程信息成功!'
          });
          this.$router.push({path: "/course/chapter/"+this.courseId})
        })
    },
    //添加课程
    addCourse() {
      course.addCourse(this.courseInfo)
        .then(res => {
          this.$message({
            type: 'success',
            message: '添加课程信息成功!'
          });
          this.$router.push({path: "/course/chapter/"+res.data.courseId})
        })
    },
    //得到教师列表
    getTeacherList() {
      teacher.getTeacherAll()
        .then(res => {
          this.teacherList = res.data.datas
        })
    },
    //得到分类列表
    getSubjectList() {
      subject.getTreeSubject()
        .then(res => {
          this.subjectOneList = res.data.list
        })
    }
  }
}
</script>

<style scoped>
.tinymce-container {
  line-height: 29px;
}
</style>
