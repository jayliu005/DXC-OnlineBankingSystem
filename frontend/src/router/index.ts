import { createRouter, createWebHistory } from 'vue-router'
import MigrationHomeView from '@/views/MigrationHomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'dxconlinebanking-home',
      component: MigrationHomeView,
    },
  ],
})

export default router
