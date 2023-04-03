import { createRouter, createWebHistory } from 'vue-router'
import AuthenticationLayout from '@/layouts/AuthenticationLayout.vue'
import ApplicationLayout from '@/layouts/ApplicationLayout.vue'
import LoginView from '@/views/auth/LoginView.vue'
import RegistrationView from '@/views/auth/RegistrationView.vue'
import HomeView from '@/views/HomeView.vue'
import BasketView from '@/views/BasketView.vue'
import ProductCreateView from '@/views/ProductCreateView.vue'
import { useAuthStore } from '@/store/authStore'

const routes = [
    {
        path: '/',
        component: ApplicationLayout,
        children: [
            {
                path: '',
                component: HomeView
            },
            {
                path: 'basket',
                component: BasketView,
                beforeEnter() {
                    const authStore = useAuthStore()
                    if(!authStore.isUser) {
                        return '/'
                    }
                    return true
                }
            },
            {
                path: 'new-product',
                component: ProductCreateView,
                beforeEnter() {
                    const authStore = useAuthStore()
                    if(!authStore.isAdmin) {
                        return '/'
                    }
                    return true
                }
            }
        ]
    },
    {
        path: '/login',
        component: AuthenticationLayout,
        children: [
            {
                path: '',
                component: LoginView
            },
            {
                path: '/register',
                component: RegistrationView
            }
        ]
    }
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

router.beforeEach(() => useAuthStore().initialize())

export default router
