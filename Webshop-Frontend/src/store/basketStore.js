import {defineStore} from 'pinia'
import {computed, ref} from "vue";
import axios from "axios";
import {API_URL} from "@/api";

export const useBasketStore = defineStore('basket', () => {
    const items = ref([])
    const deliveryAddress=ref()
    const invoiceAddress=ref()
    const shippingType = ref()

    async function order(){
        await axios.post(API_URL+"baskets/order")
        getBasket()
    }
    async function getBasket(){
        const {data} = await axios.get(API_URL+"baskets")
        items.value=data.items
        deliveryAddress.value=data.deliveryAddress
        invoiceAddress.value=data.invoiceAddress
        shippingType.value=data.shippingType
    }
     async function postItem(item){
        await axios.post(API_URL+"baskets/item", item )
        await getBasket()
    }
    async function putItem(productId, item){
        await axios.put(API_URL+"baskets/item/" + productId, item  )
        await getBasket()
    }
    async function postAddress(addressObject){
       const {data}=await axios.put(API_URL+"baskets", addressObject )
        items.value=data.items
        deliveryAddress.value=data.deliveryAddress
        invoiceAddress.value=data.invoiceAddress
        shippingType.value=data.shippingType
    }
    async function deleteItem(itemId){
        await axios.delete(API_URL+"baskets/item/"+ itemId )
       await getBasket()
    }




    return {items, getBasket, postItem, deleteItem, postAddress, putItem, deliveryAddress, invoiceAddress, shippingType}
})
