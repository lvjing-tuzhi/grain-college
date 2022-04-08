<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />

    <el-tree
      ref="tree2"
      :data="treeSubject"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />

  </div>
</template>

<script>

import subject from '../../../api/edu/subject'
import { myLog } from '../../../utils/myUtile'

export default {
  name: 'List',
  data() {
    return {
      treeSubject: [],
      defaultProps: {
        children: 'children',
        label: 'title'
      },
      filterText: '',
    }
  },
  created() {
    this.getSubject()
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    },
  },
  methods: {
    //获取分类
    getSubject() {
      subject.getTreeSubject()
        .then(res => {
          myLog("subject", res)
          this.treeSubject = res.data.list
        })
    },
    //过滤搜索
    filterNode(value, data) {
      if (!value) return true
      return data.title.toLowerCase().indexOf(value.toLowerCase()) !== -1
    }
  }
}
</script>

<style scoped>

</style>
