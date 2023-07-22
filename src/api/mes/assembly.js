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

export function updateAssembly(params) {
  return request({
    url: '/api/assembly/update',
    method: 'put',
    data: params
  })
}

export function deleteAssembly(params) {
  return request({
    url: `/api/assembly/delete/${params}`,
    method: 'delete'
  })
}

export function addAssembly(params) {
  return request({
    url: '/api/assembly/insert',
    method: 'post',
    data: params
  })
}

export function getAssemblyLike(params) {
  return request({
    url: '/api/assembly/like',
    method: 'post',
    data: params
  })
}
