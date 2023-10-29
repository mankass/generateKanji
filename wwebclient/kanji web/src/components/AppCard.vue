<template>
  <q-card class="random-word" v-if="props.data">
    <div class="q-ma-md titleQuiz">{{ props.name }}</div>
    <div class="question-container row">
      <q-card class="question"> {{ props.data.question }}</q-card>
      <div>
        <q-toggle v-model="quizHint" dense class="hint">
          Показать подсказку
        </q-toggle>
        <q-card class="answer" v-if="quizHint">
          {{ props.data.hint }}
          <div v-if="props.data.percentCorrect">
            Stat:{{ props.data.percentCorrect }}%
          </div>
        </q-card>
      </div>
    </div>
    <q-card-actions

      class="row q-pt-lg inline answers-box"
      v-for="answer in props.data.listOfAnswer"
    >
      <div class="answers-box">
        <q-btn color="deep-purple-4 " ripple rounded @click="emitAnswer(answer)">
          {{ answer }}
        </q-btn>
      </div>

    </q-card-actions>
    <div class="q-pa-lg">
      <q-btn ripple rounded class="button-text" @click="getRandomStat">
        Next word
      </q-btn>
    </div>
  </q-card>
</template>

<script lang="ts" setup>
import {ref} from "vue";

const quizHint = ref<boolean>(false);
const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
  name: {
    type: String,
    required: true,
  },
});
const emit = defineEmits(["choose-answer"]);
const emitAnswer = (answer) => {
  emit("choose-answer", answer, props.data);
};
</script>

<style lang="sass" scoped>

.random-word
  margin-top: 80px
  margin-left: 100px
  display: inline-block
  background: #FFCC00
  color: #09036b
  max-width: 500px
  max-height: 400px

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

.answer
  background: blueviolet
  color: wheat

.answers-box
  margin-left: auto
  margin-right: auto

.question
  display: inline-block
  background: #5799f5
  font-size: 40px
  margin-right: 10px

.question-container
  margin-left: 40px
  margin-top: 40px

.titleQuiz
  text-align: center
</style>
