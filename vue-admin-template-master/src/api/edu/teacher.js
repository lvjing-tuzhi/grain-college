import request from '@/utils/request'

export default {

  //查询所有教师
  getTeacherAll() {
    return request({
      url: `/eduservice/teacher/findAll`,
      method: 'get',
    })
  },

  // 按条件分页查询教师
  getTeacherListPage(current, limit, teacherQuery) {
    return request({
      url: `/eduservice/teacher/pageTeacherCondition/${current}/${limit}`,
      method: 'post',
      data: teacherQuery
    })
  },
  //按Id删除教师
  deleteTeacherId(id) {
    return request({
      url: `/eduservice/teacher/${id}`,
      method: 'delete',
    })
  },
  // 添加教师
  addTeacher(teacher) {
    return request({
      url: `/eduservice/teacher/addTeacher`,
      method: 'post',
      data: teacher
    })
  },
  // 按id查询教师
  getTeacherId(id) {
    return request({
      url: `/eduservice/teacher/${id}`,
      method: 'get',
    })
  },
  // 修改教师
  updateTeacher(teacher) {
    return request({
      url: `/eduservice/teacher/updateTeacher`,
      method: 'post',
      data: teacher
    })
  }
}
