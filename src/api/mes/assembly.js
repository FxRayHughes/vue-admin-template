import request from '@/utils/request'

export function getAssemblyList() {
  return request({
    url: '/api/assembly/list',
    method: 'get'
  })
}

export function getAssemblyGetOne(params) {
  return request({
    url: `/api/assembly/get/${params}`,
    method: 'get'
  })
}
