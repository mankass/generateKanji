<template>
  <q-page-container class="row">
    <div class="column">
      <q-card class="random-word" v-if="randomWord != undefined">
        <div class="question-container">
          <q-card class="question"> {{ randomWord.word }}</q-card>

          <q-toggle v-model="showHintWord" dense class="hint">
            Показать перевод
          </q-toggle>
          <q-card class="question" v-if="showHintWord">
            {{ randomWord.translate }}
          </q-card>
      </div>
      <q-card-actions
        class="row q-pt-lg inline"
        v-for="answer in randomWord.answers"
      >
        <q-btn color="deep-purple-4" ripple rounded @click="checkAnswer(answer)"> {{ answer }}</q-btn>
      </q-card-actions>
      <div class="q-pa-lg">
        <q-btn ripple rounded class="button-text" @click="getRandomWord"> Next word</q-btn>
      </div>
    </q-card>

      <q-card class="random-word" v-if="randomTranslateWordView">
        <div class="question-container">
          <q-card class="question"> {{ randomTranslateWordView.word }}</q-card>
          <q-toggle v-model="showHintTranslate" dense class="hint">
            Показать транскрипцию
          </q-toggle>
          <q-card class="question" v-if="showHintTranslate">
            {{ randomTranslateWordView.transcription }}
          </q-card>
        </div>
        <q-card-actions
        class="row q-pt-lg inline"
        v-for="answer in randomTranslateWordView.answers"
      >
          <q-btn ripple rounded color="deep-purple-4" @click="randomTranslateQuiz(answer)">
            {{ answer }}
          </q-btn>
        </q-card-actions>
        <div class="q-pa-lg">
          <q-btn class="button-text" ripple rounded @click="getRandomTranslateWordView">
            Next word
          </q-btn>
        </div>
      </q-card>
      <q-card class="random-word" v-if="randomWordAndStat">
        <div>My QUIZ</div>
        <div class="question-container">
          <q-card class="question"> {{ randomWordAndStat.word }}</q-card>
          <q-toggle v-model="randomMyQuizHint" dense class="hint">
            Показать перевод
          </q-toggle>
          <q-card class="question" v-if="randomMyQuizHint">
            {{ randomWordAndStat.translate }}
            <div> Stat:{{ randomWordAndStat.percentCorrect }}%</div>
          </q-card>
        </div>
        <q-card-actions
            class="row q-pt-lg inline"
            v-for="answer in randomWordAndStat.answers"
        >
          <q-btn color="deep-purple-4" ripple rounded @click="chechAnswerMyQuiz(answer)"> {{ answer }}</q-btn>
        </q-card-actions>
        <div class="q-pa-lg">
          <q-btn ripple rounded class="button-text" @click="getRandomStat"> Next word</q-btn>
        </div>

      </q-card>
    </div>
    <div class="q-ml-xl q-mt-xl">
      <div>
        <q-table
            :rows-per-page-options="[15, 25]"
            flat
            bordered
            title="Leaderboard"
            :rows="leaderboard"
            :columns="columnsTest"
            row-key="name"
            color="amber"
        ></q-table>
      </div>
    </div>
  </q-page-container>
</template>

<script lang="ts" setup>
import {
  APIApi,
  Configuration,
  LeaderboardUnitView,
  RandomTranslateWordView,
  RandomWordView,
  WordAndStatAPIApi,
  WordAndStatShared,
  WordAndStatViewRandom,
} from "../../../generated";
import {ref} from "vue";
import {useQuasar} from "quasar";

const columnsTest = [
  {
    name: 'index',
    label: '#',
    field: 'index'
  },
  {
    name: "login",
    label: "Name",
    align: "left",
    field: "login",
  },
  {
    name: "Percent",
    label: "Percent",
    align: "left",
    field: "percentCorrect",
    sortable: true,
  }
]
const api = new APIApi();
const randomWord = ref<RandomWordView>();
const randomMyQuizHint = ref<boolean>(false)
const randomTranslateWordView = ref<RandomTranslateWordView>();
const leaderboard = ref<LeaderboardUnitView[]>()
const $q = useQuasar();
const showHintWord = ref<boolean>();
const showHintTranslate = ref<boolean>();
const wordAndStatApi = new WordAndStatAPIApi(
    new Configuration({
      headers: {
        Authorization: "Bearer " + localStorage.getItem("jwt"),
      },
    })
);
const randomWordAndStatShared = ref<WordAndStatShared>();
const randomWordAndStat = ref<WordAndStatViewRandom>();

async function getRandomWord() {
  randomWord.value = await api.getRandomWord();
}

async function getRandomTranslateWordView() {
  randomTranslateWordView.value = await api.getRandomTranslateQuiz();
}

async function getRandomStat() {
  randomWordAndStat.value = await wordAndStatApi.getRandomWordAndStat();
}

async function getLeaderboard() {
  leaderboard.value = await api.getLeaderboard()
}

async function checkAnswer(answer: string) {
  if (randomWord.value.correctAnswer === answer) {

    showHintWord.value = false;
    await wordAndStatApi.createFromRandom({
      randomWordView: randomWord.value,
    });
    await getRandomWord();
    return $q.notify({
      message: "Answer correct.",
      color: "secondary",
      timeout: 22,
    });
  } else {
    await wordAndStatApi.createWordAndStat({
      wordAndStatShared: randomWordAndStatShared.value,
    });
    showHintWord.value = true;
    return $q.notify({
      message: "Answer incorrect.",
      color: "red",
    })
  }
}

function chechAnswerMyQuiz(answer: string) {
  console.log("tut")
  if (randomWordAndStat.value.correctAnswer === answer) {
    randomWordAndStat.value.correctAttempts = randomWordAndStat.value.correctAttempts + 1
    wordAndStatApi.updateAfterQuiz({
      wordAndStatViewRandom: randomWordAndStat.value

    })
    getRandomStat()
    return $q.notify({
      message: "Answer correct.",
      color: "secondary",
      timeout: 22,
    });
  } else {
    randomWordAndStat.value.wrongAttempts = randomWordAndStat.value.wrongAttempts + 1
    wordAndStatApi.updateAfterQuiz({
      wordAndStatViewRandom: randomWordAndStat.value

    })
    return $q.notify({
      message: "Answer incorrect.",
      color: "red",
    })
  }

}


function randomTranslateQuiz(answer: string) {
  if (randomTranslateWordView.value.correctAnswer === answer) {
    showHintTranslate.value = false;
    getRandomTranslateWordView();
    return $q.notify({
      message: "Answer correct.",
      color: "secondary",
      timeout: 22,
    });
  } else {
    showHintTranslate.value = true;
    return $q.notify({
      message: "Answer incorrect.",
      color: "red",
    });
  }
}

getRandomWord()
getRandomStat()
getLeaderboard()
getRandomTranslateWordView()
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
