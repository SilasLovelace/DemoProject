import { defineStore } from 'pinia'
import axios from 'axios'
import { API_URL } from '@/api'

export const useAuthStore = defineStore('auth', {
    state: () => ({
        isInitialized: false,
        user: null
    }),
    getters: {

        isLoggedIn() {
            return !!this.user
        },

        isAdmin() {
            return this.user?.role === 'admin'
        },

        isUser() {
            return this.user?.role === 'user'
        }
    },
    actions: {

        async initialize() {
            if(!this.isInitialized) {

                this.isInitialized = this.loadUser().catch(() => undefined)
            }

            await this.isInitialized
            this.isInitialized = true
        },
        async loadUser() {

            if(!localStorage.getItem('jwt')) {
                return
            }
            const {data} = await axios.get(API_URL + 'auth')
            this.applyAuthentication(data)
        },
        async login(credentials) {
            const {data} = await axios.post(API_URL + 'auth/login', credentials)
            this.applyAuthentication(data)
        },
        async register(registration) {
            const {data} = await axios.post(API_URL + 'auth/register', registration)
            this.applyAuthentication(data)
        },
        logout() {
            this.user = null
            localStorage.removeItem('jwt')
        },
        applyAuthentication({user, jwt}) {
            this.user = user
            localStorage.setItem('jwt', jwt)
        }
    }
})
