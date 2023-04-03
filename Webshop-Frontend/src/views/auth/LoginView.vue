<template>
    <form @submit.prevent="login">
        <v-card-item>
            <h1 class="mb-4">Login</h1>
            <v-alert color="error" class="mb-4" closable v-model="showInvalidCredentialsWarning">
                <p>Falsche E-Mail-Adresse oder Password</p>
            </v-alert>
            <v-text-field label="E-Mail-Adresse" type="email" required variant="outlined" maxlength="255"
                          autocomplete="email" v-model="credentials.email"/>
            <v-text-field label="Passwort" type="password" required variant="outlined" maxlength="255" minlength="8"
                          autocomplete="password" class="mb-4" v-model="credentials.password"/>
            <v-btn type="submit" color="primary" block class="mb-2">Login</v-btn>
            <v-btn to="/register" variant="text" color="primary" block>Registrieren</v-btn>
            <v-btn to="/" variant="text" block>Zurück zur Startseite</v-btn>
            <v-snackbar v-model="showUnknownError" location="bottom end">
                Ein unbekannter Fehler ist aufgetreten. Bitte versuchen Sie es später erneut.
            </v-snackbar>
        </v-card-item>
    </form>
</template>

<script setup>
    import { useAuthStore } from '@/store/authStore'
    import { ref } from 'vue'
    import { useRouter } from 'vue-router'
    import { isAxiosError } from 'axios'

    const authStore = useAuthStore()
    const showInvalidCredentialsWarning = ref(false)
    const showUnknownError = ref(false)
    const router = useRouter()
    const credentials = ref({
        email: '',
        password: ''
    })

    async function login () {
        try {
            await authStore.login(credentials.value)
            await router.push('/')
        } catch (err) {
            if (isAxiosError(err) && err.response?.status === 401) {
                return showInvalidCredentialsWarning.value = true
            }
            console.error(err)
            showUnknownError.value = true
        }
    }
</script>
