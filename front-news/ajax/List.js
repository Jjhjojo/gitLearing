/**
 * Created by lonelydawn on 2018/9/7.
 */

import List from '@/data/List'

export default {
  getListByCategory (cb, { category }) {
    setTimeout(() => {
      let result = []
      switch (category) {
        case '':
          result = List.data
          break
        case '推荐':
          result = List.data.filter(item => item.isRec)
          break
        case '关注':
          result = List.data.filter(item => item.isFollow)
          break
        default:
          result = List.data.filter(item => item.category === category)
          break
      }
      cb(result)
    }, 10)
  },
  getListByKeyword (cb, { keyword }) {
    setTimeout(() => {
      cb(List.data.filter(item => item.title.indexOf(keyword) > -1))
    }, 10)
  }
}
