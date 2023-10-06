<template>

  <q-page-container>


    <q-card>

    </q-card>



  </q-page-container>



</template>





<script lang="ts" setup>
import {APIApi} from "../../../generated";
import {ref} from "vue";

const api = new APIApi();
const randomWord= ref<>()

   async function getRandomWord(){
    randomWord.value= await api.getRandomWord()
  }

</script>

<style scoped></style>
