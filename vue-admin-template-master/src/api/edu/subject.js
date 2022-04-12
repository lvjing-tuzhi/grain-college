import request from '@/utils/request'

export default {

  // 按条件分页查询教师
  getTreeSubject() {
    return request({
      url: `/eduservice/eduSubject/getAllSubject`,
      method: 'get',
    })
  },
}
