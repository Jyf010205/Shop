import { get, post } from '@/utils/request'

export function login (username, password) {
  return post('/systemApi/admin/login', {
    username,
    password
  })
}

export function getInfo (token) {
  return get('/systemApi/admin/authentication')
}
