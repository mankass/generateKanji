<template>

  <q-page-container>


    <q-card class="random-word" v-if="randomWord!=undefined">
      <div class="row">
        <q-card> {{ randomWord.word }}</q-card>
        <q-btn class="q-pr-lg" @click="showHint()"> Показать перевод</q-btn>
      </div>
      <q-card-actions class=" row q-pt-lg" v-for="answer in randomWord.answers">
        <q-btn
        @click="checkAnswer(answer)"
        > {{answer}}</q-btn>
      </q-card-actions>
      <div class="q-pa-lg "><q-btn class="button-text" @click="getRandomWord"> Next word</q-btn></div>
    </q-card>


  </q-page-container>


</template>


<script lang="ts" setup>

import {APIApi, RandomWordView, WordData} from "../../../generated";
import {ref} from "vue";
import {useQuasar} from "quasar";

const api = new APIApi();
const randomWord = ref<RandomWordView>()
const $q = useQuasar()
async function getRandomWord() {
  randomWord.value = await api.getRandomWord()
}

function checkAnswer(answer:string){
  if (answer==randomWord.value.transcription){
    getRandomWord()
    return $q.notify({
      message: 'Answer correct.',
      color: 'secondary',
      timeout: 15
    })
  } else {
    return $q.notify({
      message: 'Answer incorrect.',
      color: 'red'
    })
  }
}
function showHint(){
  return $q.notify({
    message: randomWord.value.translate,
    color: 'green'
  })
}

getRandomWord()
</script>

<style lang="sass" scoped>

.random-word
  //height: 200px
  //width: 200px
  background: #1976d2
  color: #c1a100
.button-text
  background: #21ba45
  color: bisque

</style>
