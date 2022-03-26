<template>
  <div class="app-container">
    讲师表单
    <el-form label-width="120px">
      <el-form-item label="讲师名称">
        <el-input v-model="teacher.name"/>
      </el-form-item>
      <el-form-item label="讲师排序">
        <el-input-number v-model="teacher.sort" controls-position="right" min="0"/>
      </el-form-item>
      <el-form-item label="讲师头衔">
        <el-select v-model="teacher.level" clearable placeholder="请选择">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>
      <el-form-item label="讲师资历">
        <el-input v-model="teacher.career"/>
      </el-form-item>
      <el-form-item label="讲师简介">
        <el-input v-model="teacher.intro" :rows="10" type="textarea"/>
      </el-form-item>

<!--      &lt;!&ndash; 讲师头像：TODO &ndash;&gt;-->
<!--      &lt;!&ndash; 讲师头像 &ndash;&gt;-->
<!--      <el-form-item label="讲师头像">-->

<!--        &lt;!&ndash; 头衔缩略图 &ndash;&gt;-->
<!--        <pan-thumb :image="teacher.avatar"/>-->
<!--        &lt;!&ndash; 文件上传按钮 &ndash;&gt;-->
<!--        <el-button type="primary" icon="el-icon-upload" @click="imagecropperShow=true">更换头像-->
<!--        </el-button>-->

<!--        &lt;!&ndash;-->
<!--    v-show：是否显示上传组件-->
<!--    :key：类似于id，如果一个页面多个图片上传控件，可以做区分-->
<!--    :url：后台上传的url地址-->
<!--    @close：关闭上传组件-->
<!--    @crop-upload-success：上传成功后的回调 -->
<!--      <input type="file" name="file"/>-->
<!--    &ndash;&gt;-->
<!--        <image-cropper-->
<!--          v-show="imagecropperShow"-->
<!--          :width="300"-->
<!--          :height="300"-->
<!--          :key="imagecropperKey"-->
<!--          :url="BASE_API+'/eduoss/fileoss'"-->
<!--          field="file"-->
<!--          @close="close"-->
<!--          @crop-upload-success="cropSuccess"/>-->
<!--      </el-form-item>-->

      <el-form-item>
        <el-button :disabled="saveBtnDisabled" type="primary" @click="saveOrUpdate">保存</el-button>
      </el-form-item>
    </el-form>

  </div>
</template>

<script>


import teacher from '@/api/edu/teacher'
import { myLog } from '../../../utils/myUtile'

export default {
  name: 'Save',
  data() {
    return {
      teacherId: null,
      teacher: {},
      saveBtnDisabled: false,
      isSave: true,
    }
  },
  created() {
    let id = this.$route.params.id
    if (id != null) {
      this.isSave = false
      this.teacherId = id
      this.getTeacher()
    }else {
      this.teacher = {}
    }

  },
  methods: {
    saveOrUpdate() {
      if (this.isSave) {
        this.saveTeacher()
      }else {
        this.editTeacher()
      }
    },
    // 添加教师
    saveTeacher() {
      teacher.addTeacher(this.teacher).then(res => {
        if (res.success) {
          this.$message({
            type: 'success',
            message: '添加成功!'
          });
        }else {
          this.$message({
            type: 'error',
            message: '添加失败!'
          });
        }
      })
    },
    // 修改教师
    editTeacher() {
      teacher.updateTeacher(this.teacher).then(res => {
        if (res.success) {
          this.$message({
            type: 'success',
            message: '修改成功!'
          });
        }else {
          this.$message({
            type: 'error',
            message: '修改失败!'
          });
        }
      })
    },
    // 按id查询教师
    getTeacher() {
      teacher.getTeacherId(this.teacherId).then(res => {
        this.teacher = res.data.params
      })
    }
  }
}
</script>

<style scoped>

</style>
