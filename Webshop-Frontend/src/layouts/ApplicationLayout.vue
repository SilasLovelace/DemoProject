<template>
    <v-app-bar color="primary">
        <v-app-bar-title >WebShop</v-app-bar-title>
        <v-toolbar-items>
            <v-btn to="/">Home</v-btn>
            <v-btn to="/basket" v-if="authStore.isUser&&basketitems==0">Warenkorb</v-btn>
            <v-btn to="/basket" v-if="authStore.isUser && basketitems!=0">Warenkorb ({{ basketitems }})</v-btn>
            <v-btn to="/new-product" v-if="authStore.isAdmin">Produkt anlegen</v-btn>
            <template v-if="authStore.isLoggedIn">
                <v-btn to="/" @click="authStore.logout">Logout</v-btn>
            </template>
            <template v-else>
                <v-btn to="/login">Login</v-btn>
                <v-btn to="/register">Registrieren</v-btn>
            </template>
        </v-toolbar-items>
    </v-app-bar>
    <v-main>
        <v-container>
            <router-view/>
        </v-container>
    </v-main>
</template>

<script setup>
import {useBasketStore} from "@/store/basketStore";
import {useRoute, useRouter} from "vue-router";
import {useAuthStore} from '@/store/authStore'
import {computed} from "vue";

const authStore = useAuthStore()
const basketStore = useBasketStore()
const basketitems = computed(() => {
    let sum = 0
    basketStore.items.forEach(e => {
        if (e.amount == null) {
            sum += 1
        } else {
            sum += parseInt(e.amount)
        }
    })
    return sum
})
</script>
