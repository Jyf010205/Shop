import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

const service = axios.create({
  baseURL: process.env.VUE_APP_BASE_API,
  timeout: 5000
})

service.interceptors.request.use(
  config => {
    if (getToken()) {
      config.headers['Authorization'] = `Bearer ${getToken()}`
    }
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

service.interceptors.response.use(
  response => {
    console.log('=======================')
    console.log(response.config.baseURL + response.config.url)
    console.log(response.data)
    console.log('=======================')

    return response.data
  },
  error => {
    if (error === undefined || error.code === 'ECONNABORTED') {
      Message({
        message: '服务请求超时',
        type: 'error'
      })
      return Promise.reject(error)
    }
    const { status, data } = error.response || {}
    const { message = '服务器发生错误' } = data || {}
    if (status === 401) {
      store.dispatch('user/resetToken').then(() => router.push('/login'))
    }
    Message({
      message: message || 'Error',
      type: 'error'
    })
    return Promise.reject(error)
  }
)

/**
 * GET
 * @param url
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
export const get = (url, params = {}) => {
  return service.get(url, { params })
}

/**
 * POST
 * @param url
 * @param data
 * @param config 配置项
 * @returns {Promise<AxiosResponse<T>>}
 */
export const post = (url, data = {}, config = {}) => {
  return service.post(url, data, config)
}

/**
 * PUT
 * @param url
 * @param data
 * @returns {Promise<AxiosResponse<T>>}
 */
export const put = (url, data = {}) => {
  return service.put(url, data)
}

/**
 * PATCH
 * @param url
 * @param data
 * @returns {Promise<AxiosResponse<T>>}
 */
export const patch = (url, data = {}) => {
  return service.patch(url, data)
}

/**
 * DELETE
 * @param url
 * @param params
 * @returns {Promise<AxiosResponse<T>>}
 */
export const del = (url, params = {}) => {
  return service.delete(url, { params })
}
