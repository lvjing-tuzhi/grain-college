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
}
