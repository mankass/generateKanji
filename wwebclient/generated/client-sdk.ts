import { ref } from 'vue'
import { Loading,Notify } from 'quasar'
import {APIApi, Configuration, ResponseError} from ".penapi-generator";
import { boot } from 'quasar/wrappers'

export * from "./.penapi-generator"

export const isLoading:{[index: string]:any}= ref({})

function isDev(){
    return window.location.hostname === 'localhost'
}
export class ClientSdk{
    static request: APIApi
}

function setupAPi(){
    return new APIApi(
        new Configuration({
            basePath: isDev() ? undefined : window.location.origin,
            headers:{
                'X-CSRF-Token': "l5824xNMAYFesBxing975yR8HPJlHZ"
            }
        })
    )
}

export default boot (async ({}) =>{
    const api=new APIApi(
        new Configuration({
            basePath: isDev() ? undefined : window.location.origin,
            headers:{
                'X-CSRF-Token':'l5824xNMAYFesBxing975yR8HPJlHZ'
            }
        })
    )

    ClientSdk.request =setupAPi()
})
export async function safe(
    f:()=> Promise<void>,
    opts?:{loading: boolean, loadingName?: string}
){
    if (opts && opts.loading){
        isLoading.value[opts.loadingName]=true
    }

    if (!opts||opts.loading){
        Loading.show({message:'Loading data'})
    }
    try {
        await f()
    }catch (e){
        console.error(e)
        if (e instanceof  ResponseError){
            const content= await e.response.json()

            Notify.create({
                message: content.message,
                type: 'negative',
                position: 'top-right',
                icon:'bi-info-circle',
                timeout: 6000
            })
        }
    }finally {
        if (opts && opts.loadingName){
            isLoading.value[opts.loadingName] = false
        }

        if (!opts || opts.loading){
            Loading.hide()
        }
    }


}
