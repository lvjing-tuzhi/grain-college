import request from '@/utils/request'

export default {

  // 添加课程
  addCourse(course) {
    return request({
      url: `/eduservice/eduCourse/addCourse`,
      method: 'post',
      data: course
    })
  },
  // 修改课程
  updateCourse(course) {
    return request({
      url: `/eduservice/eduCourse/updateCourse`,
      method: 'post',
      data: course
    })
  },
  // 根据课程id查询课程
  getCourseById(courseId) {
    return request({
      url: `/eduservice/eduCourse/getById/${courseId}`,
      method: 'get',
    })
  },
  //获取课程确认界面
  getPublic(courseId) {
    return request({
      url: `/eduservice/eduCourse/getPublicCourse/${courseId}`,
      method: 'get'
    })
  },
  //课程发布
  publicCourse(courseId) {
    return request({
      url: `/eduservice/eduCourse/publish/${courseId}`,
      method: 'post'
    })
  },
  // 按条件分页查询课程
  getTeacherListPage(current, limit, courseQuery) {
    return request({
      url: `/eduservice/eduCourse/pageCourseCondition/${current}/${limit}`,
      method: 'post',
      data: courseQuery
    })
  },
  //删除课程
  deleteCourseById(courseId) {
    return request({
      url: `/eduservice/eduCourse/${courseId}`,
      method: 'delete',
    })
  }
}
