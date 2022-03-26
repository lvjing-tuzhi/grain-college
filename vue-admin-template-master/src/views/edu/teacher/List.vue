<template>
  <div class="app-container">

    <!--分页按条件查询教师-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="teacherQuery.name" placeholder="讲师名"/>
      </el-form-item>

      <el-form-item>
        <el-select v-model="teacherQuery.level" clearable placeholder="讲师头衔">
          <el-option :value="1" label="高级讲师"/>
          <el-option :value="2" label="首席讲师"/>
        </el-select>
      </el-form-item>

      <el-form-item label="添加时间">
        <el-date-picker
          v-model="teacherQuery.begin"
          type="datetime"
          placeholder="选择开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="teacherQuery.end"
          type="datetime"
          placeholder="选择截止时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"
        />
      </el-form-item>

      <el-button type="primary" icon="el-icon-search" @click="getList()">查询</el-button>
      <el-button type="default" @click="resetData()">清空</el-button>
    </el-form>

    <!-- 教师列表表格 -->
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

      <el-table-column prop="name" label="名称" width="80" />

      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level===1?'高级讲师':'首席讲师' }}
        </template>
      </el-table-column>

      <el-table-column prop="intro" label="资历" />

      <el-table-column prop="gmtCreate" label="添加时间" width="160"/>

      <el-table-column prop="sort" label="排序" width="60" />

      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/teacher/edit/'+scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit">修改</el-button>
          </router-link>
          <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeDataById(scope.row.id)">删除</el-button>
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

import teacher from '@/api/edu/teacher'
import { myLog } from '../../../utils/myUtile'

export default {
  name: 'List',
  data() {
    return {
      list: null, //教师列表
      current: 1, //当前页
      limit: 8, //每页多少
      total: 0, //总计多少条记录
      teacherQuery: {} //查询条件
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 按条件分页查询教师
    getList(page = 1) {
      this.current = page
      teacher.getTeacherListPage(this.current, this.limit, this.teacherQuery).then(res => {
        this.list = res.data.rows
        this.total = res.data.total
        myLog("教师列表", res)
      })
    },
    // 清空
    resetData() {
      this.teacherQuery = {}
      this.getList()
    },
    // 删除方法
    removeDataById(id) {
      this.$confirm('此操作将永久删除讲师记录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {  //点击确定，执行then方法
        //调用删除的方法
        teacher.deleteTeacherId(id)
          .then(res =>{//删除成功
            //提示信息
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
                message: '添加成功!'
              });
            }
          })
      }) //点击取消，执行catch方法
    }
  }
}
</script>

<style scoped>

</style>
