import { get } from '@/utils/request'

export function getProductList (params) {
  return get('/shopApi/product', params)
}
