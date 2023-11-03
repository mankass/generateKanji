<template>
  <q-card class="q-card-deck">
    <q-form v-model="props.data.name" class="q-ml-lg deck-name bold q-pt-xs"
    >Name: {{ props.data.name }}
    </q-form>

    <q-card-actions>
      <q-btn
        color="grey"
        round
        flat
        dense
        :icon="expanded ? 'visibility_off' : 'visibility'"
        @click="expanded = !expanded"
      ></q-btn>
      <q-btn class="btn-deck glossy" @click="openAppWord(props.data.id)"
      >Add word
      </q-btn>
      <q-btn class="btn-deck glossy" @click="deleteDeck(props.data.id)"
      >Remove
      </q-btn>
      <q-btn class="btn-deck glossy" @click="showPrivacy = true">Privacy</q-btn>
    </q-card-actions>

    <q-slide-transition>
      <div v-show="expanded">
        <div>
          <q-card-section class="text-subtitle2">
            <div v-if="data.listWords.length < 1">
              <q-card class="no-border empty-deck">Колода пустая</q-card>
            </div>

            <q-card
              v-else
              class="row deck-action q-mt-xs"
              v-for="word in props.data.listWords"
            >
              <q-card class="q-pt-xs content-container row">
                <p class="word-text q-pl-md q-pt-xs">
                  {{ word.wordData.word }}
                </p>
                <p class="word-text q-pl-md q-pt-xs">
                  {{ word.wordData.translate }}
                </p>
                <p class="word-text q-pl-md q-pt-xs">
                  - [{{ word.wordData.transcription }}]
                </p>
                <p class="word-text q-pl-md q-pt-xs q-pr-md">
                  Stat:{{ word.percentCorrect }}
                </p>
              </q-card>

              <q-btn class="q-ml-lg delete-btn" color="red-5"
              >Delete from deck
              </q-btn>
            </q-card>
          </q-card-section>
        </div>
      </div>
    </q-slide-transition>
  </q-card>
  <q-dialog v-model="showPrivacy">
    <privasu-list
      :deck-id="props.data.id"
      :data="props.data.listUsers"
    ></privasu-list>
  </q-dialog>
  <q-dialog v-model="showAddWord">
    <add-word @choose-word="add"></add-word>
  </q-dialog>
</template>

<script lang="ts" setup>
import {APIQUIZApi, Configuration, DeckAPIApi, WordAndStatAPIApi} from "../../../generated";
import {ref} from "vue";
import PrivasuList from "components/PrivasuList.vue";
import AddWord from "components/AddWord.vue";

const showAddWord = ref<boolean>(false);

const showPrivacy = ref<boolean>(false);

const expanded = ref(false);

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});
let deciId = ref<string>('')
const deckApi = new DeckAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);
const wordAndStatApi = new WordAndStatAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);

async function openAppWord(idDeck: string) {
  showAddWord.value = true
  deciId.value = idDeck

}

async function add(idWord: string) {
  await deckApi.addWordToDeck({
    idDeck: deciId.value,
    wordId: idWord
  })
  showAddWord.value = false
  //TODO сделать эмит на обновление дэк

}


async function deleteDeck(deckId: string) {
  await deckApi.delete1({
    id: deckId,
  });
}
</script>

<style scoped lang="sass">

.content-container
  font-size: 15px
  margin: 3px 3px 3px 3px
  background: none

.slide-deck
  color: #000000
  background: #f2c037

.q-card-deck
  background: rgba(25, 145, 210, 0.47)

.q-card-deck-2
  background: #1976d2

.empty-deck
  background: none
  border: none

.deck-action
  background: linear-gradient(to right, #0dafef, rgba(248, 76, 105, 0.76))

.words-container
  margin: 3px 3px 3px 3px
  align-items: center
  background: #26a69a

.word-text
  color: whitesmoke
  width: 120px

.appDeck
  width: 80%

.delete-btn
  margin: 3px 3px 3px auto
  border-radius: 25% 10%

.deck-name
  font-size: 20px
  font-family: Elephant, serif

.btn-deck
  border-radius: 0 10px
  color: #26a69a
  background: wheat
  margin-left: 6px
</style>
