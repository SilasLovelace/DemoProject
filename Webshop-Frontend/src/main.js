import App from './App.vue'
import { createApp } from 'vue'
import vuetify from '@/plugins/vuetify'
import router from '@/router'
import { loadFonts } from '@/plugins/webfontloader'
import { createPinia } from 'pinia'
import axios from 'axios'

/**
 * Interceptors sind Funktionen, die pro Request ausgeführt werden und dazu dienen, den jeweiligen Request zu manipulieren.
 * In diesem Fall dient er dazu, bei jedem Request den Authentication-Header zu setzen, um auch gegenüber dem Backend eingeloggt zu sein.
 *
 * Siehe: https://axios-http.com/docs/interceptors
 */
axios.interceptors.request.use(request => {
    const jwt = localStorage.getItem('jwt')
    if(!request.headers.hasAuthorization()) {
        request.headers.setAuthorization(jwt)
    }
    return request
})

loadFonts()
createApp(App)
    .use(vuetify)
    .use(createPinia())
    .use(router)
    .mount('#app')

