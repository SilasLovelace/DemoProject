<template>
    <v-container>
        <v-list-item v-if="basketStore.items.length===0">No items yet</v-list-item>

    </v-container>
    <v-container v-if="basketStore.items.length!==0">
        <v-row no-gutters>
            <v-col
                v-for="item in basketStore.items"
                :key="item.productId"
                cols="12"
                sm="4"
            >
                <v-sheet class="ma-2 pa-2">
                    <v-card variant="outlined">
                        <v-img
                            class="align-end text-white"
                            height="200"
                            :src="productStore.products.find(e => e.productId === item.productId)?.imageUrl"
                            cover>
                            <v-card-title>{{ productStore.products.find(e => e.productId === item.productId).title }}
                            </v-card-title>
                        </v-img>
                        <v-card-text>
                            Price : € {{ productStore.products.find(e => e.productId === item.productId).price }}
                        </v-card-text>
                        <v-text-field label="Amount" type="text" v-model="item.amount"></v-text-field>
                        <v-text-field label="Remark" type="text" v-model="item.remark"></v-text-field>

                        <v-btn @click="editItem(item)">Speichern</v-btn>
                        <v-btn @click="deleteItem(item.productId)">Löschen</v-btn>
                    </v-card>
                </v-sheet>
            </v-col>

        </v-row>
        <v-container v-if="basketStore.items.length!==0">
            <v-list-item>Gesamtpreis: € {{ total }}</v-list-item>
            <v-btn @click="order">Kaufen</v-btn>
        </v-container>

        <v-container>
            <v-select
                v-model="basketStore.shippingType"
                label="Zustellart"
                :items="['pickup', 'delivery']"
            ></v-select>

            <v-container v-if="basketStore.shippingType==='pickup'||basketStore.shippingType==='delivery'">
                <h3>Rechnungsadresse</h3>
                <v-container>
                    <v-text-field label="Strasse" type="text" required variant="outlined" maxlength="255"
                                  autocomplete="given-name" v-model="basketStore.invoiceAddress.street"/>
                    <v-text-field label="Number" type="text" required variant="outlined" maxlength="255"
                                  autocomplete="given-name" v-model="basketStore.invoiceAddress.number"/>
                    <v-text-field label="Zip" type="text" required variant="outlined" maxlength="255"
                                  autocomplete="given-name" v-model="basketStore.invoiceAddress.zip"/>
                    <v-text-field label="City" type="text" required variant="outlined" maxlength="255"
                                  autocomplete="given-name" v-model="basketStore.invoiceAddress.city"/>
                    <v-text-field label="Country" type="text" required variant="outlined" maxlength="255"
                                  autocomplete="given-name" v-model="basketStore.invoiceAddress.country"/>
                </v-container>

            </v-container>
            <v-container v-if="basketStore.shippingType==='delivery'">

                <h3>Lieferadresse</h3>
                <v-container>
                <v-text-field label="Strasse" type="text" required variant="outlined" maxlength="255"
                              autocomplete="given-name" v-model="basketStore.deliveryAddress.street"/>
                <v-text-field label="Number" type="text" required variant="outlined" maxlength="255"
                              autocomplete="given-name" v-model="basketStore.deliveryAddress.number"/>
                <v-text-field label="Zip" type="text" required variant="outlined" maxlength="255"
                              autocomplete="given-name" v-model="basketStore.deliveryAddress.zip"/>
                <v-text-field label="City" type="text" required variant="outlined" maxlength="255"
                              autocomplete="given-name" v-model="basketStore.deliveryAddress.city"/>
                <v-text-field label="Country" type="text" required variant="outlined" maxlength="255"
                              autocomplete="given-name" v-model="basketStore.deliveryAddress.country"/>
                </v-container>
            </v-container>
        </v-container>
    </v-container>
</template>

<script setup>
import {useProductsStore} from "@/store/productsStore";
import {useAuthStore} from "@/store/authStore";
import {useBasketStore} from "@/store/basketStore";
import {computed, onBeforeMount, onMounted, ref, toRaw} from "vue";
import {it} from "vuetify/locale";

const productStore = useProductsStore()
const authStore = useAuthStore()
const basketStore = useBasketStore()

async function findItem(item) {
    return productStore.products.find(e => e.productId == item.productId)
}

function editItem(item) {
    basketStore.putItem(item.productId, {
        amount: parseInt(item.amount),
        remark: item.remark
    })
}

onMounted(async () => {
        await authStore.initialize()
        await productStore.initialize()
        await basketStore.getBasket()
    if(!basketStore.invoiceAddress){
        console.log("addressposted")
        await basketStore.postAddress(
            {
                "invoiceAddress": {
                    "street": "",
                    "number": "",
                    "zip": "",
                    "city": "",
                    "country": ""
                },
                "deliveryAddress": {
                    "street": "",
                    "number": "",
                    "zip": "",
                    "city": "",
                    "country": ""
                },
                "shippingType": "pickup"
            }
        )

    }
    }
)

async function deleteItem(productId) {
    await basketStore.deleteItem(productId)
}

async function order() {

 await basketStore.postAddress({
        "invoiceAddress": basketStore.invoiceAddress,
        "deliveryAddress": basketStore.shippingType=='pickup'? basketStore.invoiceAddress : basketStore.deliveryAddress,
        "shippingType": basketStore.shippingType
    })
}

const total = computed(() => {
    return basketStore.items.reduce((a, b) => a + (productStore.products.find(e => e.productId == b.productId).price * b.amount), 0)
})

</script>
