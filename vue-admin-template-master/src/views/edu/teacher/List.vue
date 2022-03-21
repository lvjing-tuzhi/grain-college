<template>
  <div class="app-container">
    列表
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
      current: 0, //当前页
      limit: 10, //每页多少
      total: 0, //总计多少条记录
      teacherQuery: null //查询条件
    }
  },
  created() {
    this.getList()
  },
  methods: {
    // 按条件分页查询教师
    getList() {
      teacher.getTeacherListPage(this.current, this.limit, this.teacherQuery).then(res => {
        myLog("教师列表", res)
      })
    }
  }
}
</script>

<style scoped>

</style>
