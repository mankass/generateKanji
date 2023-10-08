<template>
  <q-page-container>
    <q-card class="random-word" v-if="randomWord != undefined">
      <div class="question-container">
        <q-card class="question"> {{ randomWord.word }}</q-card>

        <q-toggle v-model="showHintWord" dense class="hint">
          Показать перевод
        </q-toggle
        >
        <q-card class="question" v-if="showHintWord">
          {{ randomWord.translate }}
        </q-card>
      </div>
      <q-card-actions
        class="row q-pt-lg inline"
        v-for="answer in randomWord.answers"
      >
        <q-btn @click="checkAnswer(answer)"> {{ answer }}</q-btn>
      </q-card-actions>
      <div class="q-pa-lg">
        <q-btn class="button-text" @click="getRandomWord"> Next word</q-btn>
      </div>
    </q-card>

    <q-card class="random-word" v-if="randomTranslateWordView != undefined">
      <div class="question-container">
        <q-card class="question"> {{ randomTranslateWordView.word }}</q-card>
        <q-toggle v-model="showHintTranslate" dense class="hint">
          Показать транскрипцию
        </q-toggle
        >
        <q-card class="question" v-if="showHintTranslate">
          {{ randomTranslateWordView.transcription }}
        </q-card>
      </div>
      <q-card-actions
        class="row q-pt-lg inline"
        v-for="answer in randomTranslateWordView.answers"
      >
        <q-btn @click="randomTranslateQuiz(answer)">
          {{ answer }}
        </q-btn>
      </q-card-actions>
      <div class="q-pa-lg">
        <q-btn class="button-text" @click="getRandomTranslateWordView">
          Next word
        </q-btn
        >
      </div>
    </q-card>
  </q-page-container>
</template>

<script lang="ts" setup>
import {
  APIApi,
  RandomTranslateWordView,
  RandomWordView,
  WordData,
} from "../../../generated";
import {ref} from "vue";
import {useQuasar} from "quasar";

const api = new APIApi();
const randomWord = ref<RandomWordView>();
const randomTranslateWordView = ref<RandomTranslateWordView>();
const $q = useQuasar();
const showHintWord = ref<boolean>();
const showHintTranslate = ref<boolean>();

async function getRandomWord() {
  randomWord.value = await api.getRandomWord();
}

async function getRandomTranslateWordView() {
  randomTranslateWordView.value = await api.getRandomTranslateQuiz();
}

function checkAnswer(answer: string) {
  if (randomWord.value.correctAnswer === answer) {
    getRandomWord()
    showHintWord.value = false
    return $q.notify({
      message: "Answer correct.",
      color: "secondary",
      timeout: 22,
    });
  } else {
    showHintWord.value = true
    return $q.notify({
      message: "Answer incorrect.",
      color: "red",
    });
  }
}

function randomTranslateQuiz(answer: string) {
  if (randomTranslateWordView.value.correctAnswer === answer) {
    showHintTranslate.value = false
    getRandomTranslateWordView()
    return $q.notify({
      message: "Answer correct.",
      color: "secondary",
      timeout: 22,
    });
  } else {
    showHintTranslate.value = true
    return $q.notify({
      message: "Answer incorrect.",
      color: "red",
    });
  }
}

getRandomWord();
getRandomTranslateWordView();
</script>

<style lang="sass" scoped>

.random-word
  margin-top: 100px
  margin-left: 100px
  display: inline-block
  background: #FFCC00
  color: #09036b

.button-text
  background: #21ba45
  color: bisque

.controller
  display: flex
  justify-content: space-between
  height: 200px

.container
  display: flex
  flex-direction: column
  justify-content: space-between

.question
  display: inline-block
  background: #5799f5
  font-size: 40px
  margin-right: 10px

.question-container
  margin-left: 40px
  margin-top: 40px
</style>
