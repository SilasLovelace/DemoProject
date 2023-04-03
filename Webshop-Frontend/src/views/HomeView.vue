<template>
    <v-list-item v-if="productStore.products.length==0">No items yet</v-list-item>
    <v-container class="d-flex justify-center" >
        <v-select class="select"
            label="Items Per Page"
            v-model="length"
            :items="[1,2,3]"
        ></v-select>
    </v-container>
    <v-pagination  v-model="page" :length="paginatorlength"></v-pagination>
    <v-container>
        <v-row no-gutters>
            <v-col
                v-for="product in pageslice"
                :key="product.productId"
                cols="12"
                sm="4"
            >
                <v-sheet class="ma-2 pa-2">
                    <v-card variant="outlined">
                        <v-img
                            class="align-end text-white"
                            height="200"
                            :src="product.imageUrl"
                            cover
                        >
                            <v-card-title>{{ product.title }}</v-card-title>
                        </v-img>
                        <v-card-text v-if="authStore.isUser">
                            Preis : € {{ product.price }} <br>
                            Beschreibung: {{product.description}}
                        </v-card-text>
                        <v-card-item v-if="authStore.isAdmin">
                            <v-container >
                            <v-text-field :disabled="!product.showEdit" label="Title" type="text" v-model="product.title"></v-text-field>
                            <v-text-field :disabled="!product.showEdit" label="Description" type="text" v-model="product.description"></v-text-field>
                            <v-text-field :disabled="!product.showEdit" label="Image-Url" type="text" v-model="product.imageUrl"></v-text-field>
                            <v-text-field :disabled="!product.showEdit" label="Price" type="text" v-model.number="product.price"></v-text-field>
                            </v-container>
                        </v-card-item>

                        <v-card-item v-if="authStore.isUser">
                            <v-text-field label="Amount" type="text" v-model="product.amount"></v-text-field>
                            <v-text-field label="Remark" type="text" v-model="product.remark"></v-text-field>
                        </v-card-item>

                        <v-card-actions v-if="authStore.isUser">
                            <v-btn variant="outlined" @click="addToBasket(product)">Buy</v-btn>
                        </v-card-actions>

                        <v-card-actions v-if="authStore.isAdmin">
                            <v-btn variant="outlined" @click="deleteProduct(product.productId)">Delete</v-btn>
                            <v-btn variant="outlined" v-if="!product.showEdit" @click="product.showEdit=!product.showEdit">Edit</v-btn>
                            <v-btn variant="outlined" v-if="product.showEdit" @click="product.showEdit=!product.showEdit; editProduct(product.productId, product)">Save</v-btn>
                        </v-card-actions>

                        <v-card-actions v-if="!authStore.isLoggedIn">
                            <v-btn variant="outlined" to="/login">Buy</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-sheet>
            </v-col>
        </v-row>
        <v-snackbar v-model="showBasketWarning" location="bottom end">
            Dises Produkt liegt derzeit in einem Warenkorb und kann daher nicht gelöscht werden!
        </v-snackbar>
    </v-container>
    <v-pagination v-model="page" :length="paginatorlength"></v-pagination>
</template>

<script setup>
import {useProductsStore} from "@/store/productsStore";
import {useAuthStore} from "@/store/authStore";
import {useBasketStore} from "@/store/basketStore";
import {computed, onMounted, ref, watch} from "vue";
import {isAxiosError} from "axios";

const productStore = useProductsStore()
const authStore = useAuthStore()
const basketStore = useBasketStore()
const showBasketWarning = ref(false)
const page= ref(1)
const length = ref(2)
 const paginatorlength = computed(()=> {
     return Math.ceil(productStore.products.length / length.value)
 })
watch(length,()=>page.value=Math.min(page.value, Math.ceil(productStore.products.length/length.value)))
const pageslice = computed(()=>{
    return productStore.products.slice((page.value-1)*length.value,length.value*page.value)
})

onMounted(async () => {
    await authStore.initialize()
    await productStore.initialize()
    if(authStore.isUser)
        basketStore.getBasket()
    }
)

async function deleteProduct(productId) {
    if (confirm("Delete this Product?")) {
        try {
            await productStore.deleteProduct(productId)
        }catch (err){
            if(isAxiosError(err) && err.response?.status === 409) {
                return showBasketWarning.value = true
            }
            console.error(err)
        }
    }
}

async function editProduct(productId, product) {
    await productStore.editProduct(productId, product)
}

async function addToBasket(product) {

    await basketStore.postItem(

        {
            productId: product.productId,
            amount: product.amount === undefined || product.amount ===  ''  ? 0 : parseInt(product.amount),
            remark: product.remark === undefined || product.remark ===  ''  ? '' : product.remark
        }
    )
    product.remark = ''
    product.amount = ''
}
</script>

<style>
.select{
    max-width: 140px;
}
</style>
