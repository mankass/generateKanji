<template>
  <q-page-container class="row">
    <div class="row">
      <AppCard
        :data="translateQuiz"
        :name="'Translate Quiz'"
        @choose-answer="checkAnswer"
      ></AppCard>
      <AppCard
        name="TranscriptionQuiz"
        :data="transcriptionQuiz"
        @choose-answer="checkAnswer"
      ></AppCard>
      <AppCard
        name="KanjiQuiz"
        :data="kanjiQuiz"
        @choose-answer="checkAnswer"
      ></AppCard>
    </div>
    <div class="column">
      <q-card class="random-word" v-if="false">
        <div class="q-ma-md titleQuiz">My QUIZ</div>
        <div class="question-container row">
          <q-card class="question"> {{ randomWordAndStat.word }}</q-card>
          <div>
            <q-toggle v-model="randomMyQuizHint" dense class="hint">
              Показать перевод
            </q-toggle>
            <q-card class="answer" v-if="randomMyQuizHint">
              {{ randomWordAndStat.translate }}
              <div>Stat:{{ randomWordAndStat.percentCorrect }}%</div>
            </q-card>
          </div>
        </div>
        <q-card-actions
          class="row q-pt-lg inline"
          v-for="answer in randomWordAndStat.answers"
        >
          <q-btn
            color="deep-purple-4"
            ripple
            rounded
            @click="checkAnswerMyQuiz(answer)"
          >
            {{ answer }}
          </q-btn>
        </q-card-actions>
        <div class="q-pa-lg">
          <q-btn ripple rounded class="button-text" @click="getRandomStat">
            Next word
          </q-btn>
        </div>
      </q-card>
    </div>
    <div class="q-ml-xl  leaderboard q-mt-md">
      <div class=" q-mt-md">
        <q-table
          :rows-per-page-options="[15]"
          flat
          class="leaderboard-list"
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
  APIQUIZApi,
  Configuration,
  LeaderboardUnitView,
  QuizView,
  QuizViewTypeQuizEnum,
  RandomTranslateWordView,
  RandomWordView,
  WordAndStatAPIApi,
  WordAndStatViewRandom,
} from "../../../generated";
import {ref} from "vue";
import {useQuasar} from "quasar";
import AppCard from "components/AppCard.vue";

const columnsTest = [
  {
    name: "slot",
    label: "Slot",
    align: "left",
    field: "slot",
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
  },
];
const api = new APIApi();
const randomWord = ref<RandomWordView>();
const randomMyQuizHint = ref<boolean>(false);
const randomTranslateWordView = ref<RandomTranslateWordView>();
const leaderboard = ref<LeaderboardUnitView[]>();
const $q = useQuasar();
const translateQuiz = ref<QuizView>();
const transcriptionQuiz = ref<QuizView>();
const kanjiQuiz = ref<QuizView>();
const wordAndStatApi = new WordAndStatAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);
const quizApi = new APIQUIZApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);
const randomWordAndStat = ref<WordAndStatViewRandom>();

async function getRandomStat() {
  randomWordAndStat.value = await wordAndStatApi.getRandomWordAndStat();
}

async function getQuizByType(quiz: QuizViewTypeQuizEnum) {
  switch (quiz) {
    case "TRANSCRIPTION": {
      transcriptionQuiz.value = await quizApi.getQuizByType({quizType: quiz})
      break
    }
    case "TRANSLATE": {
      translateQuiz.value = await quizApi.getQuizByType({quizType: quiz})
      break
    }
    case "KANJI": {
      kanjiQuiz.value = await quizApi.getQuizByType({quizType: quiz})
      break
    }
  }
}

async function getLeaderboard() {
  leaderboard.value = await api.getLeaderboard();

  while (leaderboard.value.length < 15) {
    let i = leaderboard.value.length + 1;
    let temp = ref<LeaderboardUnitView>({
      login: "Empty",
      percentCorrect: 0,
      slot: i,
    });
    leaderboard.value.push(temp.value);
    i++;
  }
}

async function checkAnswer(answer: string, quiz: QuizView) {
  console.log("ответ пришел" + answer);
  console.log(quiz);
  console.log(quiz.answer);
  console.log("type" + translateQuiz.value.typeQuiz)
  console.log(quiz.typeQuiz)
  if (quiz.answer === answer) {
    console.log(quiz.typeQuiz)
    await getQuizByType(quiz.typeQuiz)

    return $q.notify({
      message: "Answer correct.",
      color: "secondary",
      timeout: 22,
    });
  } else {
    return $q.notify({
      message: "Answer incorrect.",
      color: "red",
    });
  }
}

function checkAnswerMyQuiz(answer: string) {
  if (randomWordAndStat.value.correctAnswer === answer) {
    randomWordAndStat.value.correctAttempts =
      randomWordAndStat.value.correctAttempts + 1;
    wordAndStatApi.updateAfterQuiz({
      wordAndStatViewRandom: randomWordAndStat.value,
    });
    getRandomStat();
    return $q.notify({
      message: "Answer correct.",
      color: "secondary",
      timeout: 22,
    });
  } else {
    randomWordAndStat.value.wrongAttempts =
      randomWordAndStat.value.wrongAttempts + 1;
    wordAndStatApi.updateAfterQuiz({
      wordAndStatViewRandom: randomWordAndStat.value,
    });
    return $q.notify({
      message: "Answer incorrect.",
      color: "red",
    });
  }
}


getQuizByType("TRANSCRIPTION")
getQuizByType("TRANSLATE")
getQuizByType("KANJI")
getRandomStat();
getLeaderboard()

</script>

<style lang="sass" scoped>

.random-word
  margin-top: 80px
  margin-left: 100px
  display: inline-block
  background: #FFCC00
  color: #09036b
  max-width: 500px

.button-text
  background: #21ba45
  color: bisque

.leaderboard
  width: 400px

.leaderboard-list
  margin-right: 10px
  display: inline-block
  color: #000000
  background: #c2bff8

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
