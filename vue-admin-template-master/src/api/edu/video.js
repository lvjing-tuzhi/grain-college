import request from '@/utils/request'

export default {

  // 增加小节
  addVideo(video) {
    return request({
      url: `/eduservice/eduVideo/addVideo`,
      method: 'post',
      data: video
    })
  },
  // 修改小节
  updateVideo(video) {
    return request({
      url: `/eduservice/eduVideo/updateVideo`,
      method: 'post',
      data: video
    })
  },
  // 删除小节
  deleteVideo(id) {
    return request({
      url: `/eduservice/eduVideo/${id}`,
      method: 'delete',
    })
  },
  // 根据id查询小节
  getVideoById(id) {
    return request({
      url: `/eduservice/eduVideo/getVideoById/${id}`,
      method: 'get',
    })
  },
}
