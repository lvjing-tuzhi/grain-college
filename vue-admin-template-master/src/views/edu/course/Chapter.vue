<template>
  <div class="app-container">

    <h2 style="text-align: center;">发布新课程</h2>

    <el-steps :active="2" process-status="wait" align-center style="margin-bottom: 40px;">
      <el-step title="填写课程基本信息"/>
      <el-step title="创建课程大纲"/>
      <el-step title="最终发布"/>
    </el-steps>

    <el-button type="text" @click="openChapterDialog()">添加章节</el-button>

<!--     章节-->
    <ul class="chanpterList">
      <li
        v-for="chapter in chapterVideoList"
        :key="chapter.id">
        <p>
          {{ chapter.title }}
          <span class="acts">
            <el-button style="" type="text" @click="openVideo(chapter.id)">添加小节</el-button>
            <el-button style="" type="text" @click="openEditChatper(chapter.id)">编辑</el-button>
            <el-button type="text" @click="removeChapter(chapter.id)">删除</el-button>
          </span>
        </p>

        <!-- 视频小结 -->
        <ul class="chanpterList videoList">
          <li
            v-for="video in chapter.children"
            :key="video.id">
            <p>{{ video.title }}
              <span class="acts">
                <el-button style="" type="text" @click="openEditVideo(video.id)">编辑</el-button>
                <el-button type="text" @click="removeVideo(video.id)">删除</el-button>
              </span>
            </p>
          </li>
        </ul>
      </li>
    </ul>
    <div>
      <el-button @click="previous">上一步</el-button>
      <el-button :disabled="saveBtnDisabled" type="primary" @click="next">下一步</el-button>
    </div>

<!--     添加和修改章节表单 -->
    <el-dialog :visible.sync="dialogChapterFormVisible" title="操作章节">
      <el-form :model="chapter" label-width="120px">
        <el-form-item label="章节标题">
          <el-input v-model="chapter.title"/>
        </el-form-item>
        <el-form-item label="章节排序">
          <el-input-number v-model="chapter.sort" :min="0" controls-position="right"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogChapterFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="saveOrUpdate">确 定</el-button>
      </div>
    </el-dialog>

    <!-- 添加和修改课时小节表单 -->
    <el-dialog :visible.sync="dialogVideoFormVisible" title="添加小节">
      <el-form :model="video" label-width="120px">
        <el-form-item label="课时标题">
          <el-input v-model="video.title"/>
        </el-form-item>
        <el-form-item label="课时排序">
          <el-input-number v-model="video.sort" :min="0" controls-position="right"/>
        </el-form-item>
        <el-form-item label="是否免费">
          <el-radio-group v-model="video.free">
            <el-radio :label="true">免费</el-radio>
            <el-radio :label="false">默认</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上传视频">
          <el-upload
            :on-success="handleVodUploadSuccess"
            :on-remove="handleVodRemove"
            :before-remove="beforeVodRemove"
            :on-exceed="handleUploadExceed"
            :file-list="fileList"
            :action="BASE_API+'/eduvod/uploadVideo'"
            :limit="1"
            class="upload-demo">
            <el-button size="small" type="primary">上传视频</el-button>
            <el-tooltip placement="right-end">
              <div slot="content">
                支持3GP、ASF、AVI、DAT、DV、FLV、F4V、<br>
                GIF、M2T、M4V、MJ2、MJPEG、MKV、MOV、MP4、<br>
                MPE、MPG、MPEG、MTS、OGG、QT、RM、RMVB、<br>
                SWF、TS、VOB、WMV、WEBM 等视频格式上传</div>
              <i class="el-icon-question"/>
            </el-tooltip>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVideoFormVisible = false">取 消</el-button>
        <el-button :disabled="saveVideoBtnDisabled" type="primary" @click="saveOrUpdateVideo">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>

import chapter from '../../../api/edu/chapter'
import video from '../../../api/edu/video'
import { myLog } from '../../../utils/myUtile'

export default {
  name: 'chapter',
  data() {
    return {
      courseId: "", //课程ID
      saveBtnDisabled: false,
      saveVideoBtnDisabled: false,
      dialogChapterFormVisible: false, //添加和修改章节弹窗
      dialogVideoFormVisible: false, //添加小节弹窗
      BASE_API: process.env.BASE_API, // 服务器地址
      chapterVideoList: [], //课程大纲
      chapter: { //封装章节数据
        title: '',
        sort: 0,
        courseId: null, //课程Id
      },
      video: {}, //小节
      fileList: [] //视频文件列表
    }
  },
  created() {
    let courseId = this.$route.params.courseId
    if (courseId != null) {
      this.courseId = courseId
    }
    this.getChapter()
  },
  methods: {
    //上传视频数量超过设置数量执行的函数
    handleUploadExceed() {
      this.$message.warning('想要重新上传视频，请先删除已上传的视频')
    },
    //视频删除之前执行的函数
    beforeVodRemove() {
      return this.$confirm('此操作将删除章节, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
    },
    //beforeVodRemove函数返回true的时候就会执行这个视频移除的函数
    handleVodRemove() {
      video.deleteAliyunVideo(this.video.videoSourceId)
        .then(res => {
          this.$message({
            type: 'success',
            message: '删除视频成功!'
          })

          //把文件列表清空
          this.fileList = []
          //把video视频id和视频名称值清空
          //上传视频id赋值
          this.video.videoSourceId = ''
          //上传视频名称赋值
          this.video.videoOriginalName = ''
        })
    },
    //视频上传成功执行的函数
    handleVodUploadSuccess(response, file, fileList) {
      //上传视频id赋值
      this.video.videoSourceId = response.data.videoId
      //上传视频名称赋值
      this.video.videoOriginalName = file.name
      myLog("===", fileList)
    },
    //返回上一步课程基本信息界面
    previous() {
      this.$router.push({path: "/course/info/"+this.courseId})
    },
    // 下一步
    next() {
      this.$router.push({path: "/course/publish/"+this.courseId})
    },
    // 打开添加章节弹窗
    openChapterDialog() {
      this.dialogChapterFormVisible = true
      this.chapter.title = ''
      this.chapter.sort = '0'
    },
    //打开编辑章接弹窗
    openEditChatper(id) {
      this.dialogChapterFormVisible = true
      this.getChapterById(id)
    },
    //打开添加小节弹窗
    openVideo(chapterId) {
      this.dialogVideoFormVisible = true
      this.video = {}
      this.video.chapterId = chapterId
      this.video.courseId = this.courseId
    },
    //打开编辑小节弹窗
    openEditVideo(id) {
      this.dialogVideoFormVisible = true
      this.getVideo(id)
    },

    //保存或修改章节
    saveOrUpdate() {
      if (!this.chapterVideoList.id) {
        this.saveChapter()
      }else {
        this.updateChapter()
      }
    },
    //保存或修改小节
    saveOrUpdateVideo() {
      let video = this.video
      if (video.id != null) {
        this.updateVideo()
      }else {
        this.saveVideo()
      }
    },

    //修改小节
    updateVideo() {
      video.updateVideo(this.video)
        .then(res => {
          //关闭弹框
          this.dialogVideoFormVisible= false
          //提示
          this.$message({
            type: 'success',
            message: '修改小节成功!'
          });
          this.getChapter()
        })
    },
    //添加小节
    saveVideo() {
      this.fileList = []
      video.addVideo(this.video)
        .then(res => {
          //关闭弹框
          this.dialogVideoFormVisible= false
          //提示
          this.$message({
            type: 'success',
            message: '添加小节成功!'
          });
          this.getChapter()
        })
    },
    //删除小节
    removeVideo(id) {
      video.deleteVideo(id)
        .then(res => {
          //关闭弹框
          this.dialogVideoFormVisible = false
          //提示
          this.$message({
            type: 'success',
            message: '删除小节成功!'
          });
          this.getChapter()
        })
    },
    //根据小节id查询小节
    getVideo(id) {
      video.getVideoById(id)
        .then(res => {
          this.video = res.data.video
          myLog("video", res.data.video.videoOriginalName)
          this.fileList = [{"name": res.data.video.videoOriginalName}]

        })
    },

    //修改章节
    updateChapter() {
      chapter.updateChapter(this.chapter)
        .then(res => {
          //关闭弹框
          this.dialogChapterFormVisible = false
          //提示
          this.$message({
            type: 'success',
            message: '修改章节成功!'
          });
          this.getChapter()
        })
    },
    // 添加章节
    saveChapter() {
      this.chapter.courseId = this.courseId
      chapter.addChapter(this.chapter)
        .then(res => {
          //关闭弹框
          this.dialogChapterFormVisible = false
          //提示
          this.$message({
            type: 'success',
            message: '添加章节成功!'
          });
          this.getChapter()
        })
    },
    //根据课程ID获取课程大纲
    getChapter() {
      chapter.getChapterTree(this.courseId)
        .then(res => {
          this.chapterVideoList = res.data.list
        })
    },
    //根据章节ID查询章节
    getChapterById(id) {
      chapter.getChapterById(id)
        .then(res => {
          this.chapter = res.data.chapter
        })
    },
    //根据id删除章节
    removeChapter(id) {
      chapter.deleteChapterById(id)
        .then(res => {
          //提示
          this.$message({
            type: 'success',
            message: '删除章节成功!'
          })
          this.getChapter()
        })
    }
  }
}
</script>

<style scoped>
.chanpterList{
  position: relative;
  list-style: none;
  margin: 0;
  padding: 0;
}
.chanpterList li{
  position: relative;
}
.chanpterList p{
  float: left;
  font-size: 20px;
  margin: 10px 0;
  padding: 10px;
  height: 70px;
  line-height: 50px;
  width: 100%;
  border: 1px solid #DDD;
}
.chanpterList .acts {
  float: right;
  font-size: 14px;
}

.videoList{
  padding-left: 50px;
}
.videoList p{
  float: left;
  font-size: 14px;
  margin: 10px 0;
  padding: 10px;
  height: 50px;
  line-height: 30px;
  width: 100%;
  border: 1px dotted #DDD;
}
</style>
