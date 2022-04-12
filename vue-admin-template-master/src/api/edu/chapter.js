import request from '@/utils/request'

export default {

  // 按条件查询课程大纲
  getChapterTree(courseId) {
    return request({
      url: `/eduservice/eduChapter/getChapterThree/${courseId}`,
      method: 'get',
    })
  },
  //添加章节
  addChapter(chapter) {
    return request({
      url: `/eduservice/eduChapter/addChapter`,
      method: 'post',
      data: chapter
    })
  },
  // 修改章节
  updateChapter(chapter) {
    return request({
      url: `/eduservice/eduChapter/updateChapter`,
      method: 'post',
      data: chapter
    })
  },
  //删除章节
  deleteChapterById(id) {
    return request({
      url: `/eduservice/eduChapter/${id}`,
      method: 'delete'
    })
  },
  // 根据id查询章节
  getChapterById(id) {
    return request({
      url: `/eduservice/eduChapter/chapter/${id}`,
      method: 'get'
    })
  }

}
