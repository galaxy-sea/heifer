/** When your routing table is too long, you can split it into small modules**/

import Layout from '@/layout'

const idaasRouter = {
  path: '/idaas',
  component: Layout,
  redirect: 'noRedirect',
  alwaysShow: true,
  name: 'Idaas',
  meta: {
    title: 'idaas',
    icon: 'chart'
  },
  children: [
    {
      path: 'appleTest',
      component: () => import('@/views/idaas/appleTest'),
      name: 'AppleTest',
      meta: { title: 'appleTest', noCache: true }
    }
  ]
}

export default idaasRouter
