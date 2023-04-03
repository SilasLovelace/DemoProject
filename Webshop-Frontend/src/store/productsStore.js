import {defineStore} from 'pinia'
import {computed, ref} from "vue";
import axios from "axios";
import {API_URL} from "@/api";


export const useProductsStore = defineStore('products', () => {
    const products = ref([])
    const isInitialized = ref(false)
    const productsByCategory = computed(() => {
        products.reduce((products, product) => {
                if (!Object.hasOwn(product.category)) {
                    products[products.category]=[]
                }
                products[products.category].add(product)
                return products
            },
            {})
    })

    async function initialize() {
        if(!isInitialized.value) {
            isInitialized.value = getAllProducts().catch(() => undefined)
        }
        await isInitialized.value
        isInitialized.value = true
    }
    async function postProducts (product){
        await axios.post ( API_URL + 'products', product )
    }
    async function getAllProducts(){
        const {data} = await axios.get(API_URL + 'products')
        products.value=data
    }

    async function deleteProduct(productId){
        await axios.delete(API_URL +'products/' + productId)
        const index = products.value.findIndex(e=>e.productId===productId)
        if(index==-1){
            products.value.splice(index,1)
        }
    }

    async function editProduct(productId, product){
        const {data} = await axios.put(API_URL +'products/' + productId, product)
        const index = products.value.findIndex(e=>e.productId===data.productId)
        if(index==-1){
            products.value[index]=data
        }

    }


    return {products, productsByCategory, postProducts, getAllProducts, deleteProduct, editProduct,initialize}
})
