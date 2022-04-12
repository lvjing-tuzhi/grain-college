<template>
  <div class="app-container">
    课程列表

    <!--查询表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="courseQuery.title" placeholder="课程名称"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="courseQuery.status" clearable placeholder="课程状态">
          <el-option value="Normal" label="已发布"/>
          <el-option value="Draft" label="未发布"/>
        </el-select>
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 表格 -->
    <el-table
      :data="list"
      border
      fit
      highlight-current-row>

      <el-table-column
        label="序号"
        width="70"
        align="center">
        <template slot-scope="scope">
          {{ (current - 1) * limit + scope.$index + 1 }}
        </template>
      </el-table-column>

      <el-table-column prop="title" label="课程名称" width="80" />

      <el-table-column label="课程状态" width="80">
        <template slot-scope="scope">
          {{ scope.row.status==='Normal'?'已发布':'未发布' }}
        </template>
      </el-table-column>

      <el-table-column prop="lessonNum" label="课时数" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

      <el-table-column prop="viewCount" label="浏览数量" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/course/info/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程基本信息</el-button>
          </router-link>
          <router-link :to="'/course/chapter/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑课程大纲息</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除课程信息</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <el-pagination
      :current-page="current"
      :page-size="limit"
      :total="total"
      style="padding: 30px 0; text-align: center;"
      layout="total, prev, pager, next, jumper"
      @current-change="getList"
    />

  </div>
</template>

<script>

import course from '../../../api/edu/course'

export default {
  name: 'list',
  data() {
    return {
      limit: 6, //每页几条数据
      total: 0, //一共几条数据
      current: 1, //页数
      courseQuery: {}, //查询条件
      list: [], //课程列表
    }
  },
  created() {
    this.getList()
  },
  methods: {
    //删除操作
    removeDataById(id) {
      this.$confirm('此操作将永久删除课程记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() =>{
        this.deleteCourse(id)
      })
    },
    //清空条件搜索栏
    resetData() {
      this.courseQuery = {}
      this.getList()
    },

    //根据课程id删除课程删除课程
    deleteCourse(courseId) {
      course.deleteCourseById(courseId)
        .then(res => {
          if (res.success) {
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
            //回到列表页面
            this.getList()
          }else {
            this.$message({
              type: 'error',
              message: '删除失败!'
            });
          }
        })
    },
    //根据条件分页查询课程
    getList(page=1) {
      this.current = page
      course.getTeacherListPage(this.current, this.limit, this.courseQuery)
        .then(res => {
          this.list = res.data.list
          this.total = res.data.total
        })
    }
  }
}
</script>

<style scoped>

</style>
