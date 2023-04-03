<template>
    <form @submit.prevent="postProduct">
        <v-card-item>
            <h1 class="mb-4">Produkt Anlegen</h1>
            <v-text-field label="Produktname" type="text" required variant="outlined" maxlength="255"
                          autocomplete="given-name" v-model="product.title"/>
            <v-text-field label="Beschreibung" type="text" required variant="outlined" maxlength="255"
                          autocomplete="family-name" v-model="product.description"/>
            <v-text-field label="Preis" type="number" step="0.01" required variant="outlined" maxlength="255"
                          autocomplete="email" v-model.number="product.price"/>
            <v-text-field label="URL" type="text" required variant="outlined" maxlength="255"
                          autocomplete="new-password" class="mb-4" v-model="product.imageUrl"/>
            <v-btn @click.prevent="postProduct" type="submit" color="primary" block class="mb-2">Produkt anlegen</v-btn>
            <v-snackbar v-model="showContentWarning" location="bottom end">
                Alle felder müssen ausgefüllt sein
            </v-snackbar>
            <v-snackbar v-model="showError" location="bottom end">
                Der Preis muss im Format XX.XX sein
            </v-snackbar>
        </v-card-item>
    </form>
</template>

<script setup>
import {useProductsStore} from "@/store/productsStore";
import { ref } from 'vue'
import { useRouter } from 'vue-router'
const productsStore = useProductsStore()

const showContentWarning = ref(false)
const showError = ref(false)
const router = useRouter()
const product = ref({
    title: null,
    description: null,
    price: null,
    imageUrl: null,
})

async function postProduct () {
    if (product.value.title && product.value.description && product.value.price && product.value.imageUrl){
        if (!/^\d+(\.\d{1,2})?$/.test(product.value.price)) {
            showError.value = true
        } else {
            await productsStore.postProducts(product.value)
            await router.push('/')
        }} else {
         showContentWarning.value = true
    }


}
</script>

