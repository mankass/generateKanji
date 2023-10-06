<template>

  <q-page-container>


    <q-card class="random-word">

    </q-card>



  </q-page-container>



</template>


<script lang="ts" setup>

import {APIApi, WordData} from "../../../generated";
import {ref} from "vue";

const api = new APIApi();
const randomWord= ref<WordData>()

async function getRandomWord(){
  randomWord.value= await api.getRandomWord()
}
getRandomWord()
</script>

<style lang="sass" scoped>

.random-word
  height: 200px
  width: 200px
  background: #1976d2
  color: #c10015



</style>
