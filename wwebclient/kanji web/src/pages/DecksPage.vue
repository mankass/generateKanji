<template>
  <q-page-container class="q-page-container">
    <div class="deck-container">
      <div class="row menuDeck q-mt-lg-lg">
        <q-btn class="menu-btn" @click="createDeck()" color="orange"
        >Create Deck
        </q-btn>
      </div>
      <div class="decks-container">
        <q-list class="list-decks">
          <q-card class="q-mt-xs" v-for="deck in decksList">
            <app-deck :data="deck"></app-deck>
          </q-card>
        </q-list>
      </div>
    </div>
  </q-page-container>
</template>

<script lang="ts" setup>
import {Configuration, DeckAPIApi, DeckView} from "../../../generated";
import {ref} from "vue";
import AppDeck from "components/AppDeck.vue";
import AddWord from "components/AddWord.vue";

const deckApi = new DeckAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);
const decksList = ref<DeckView[]>();

async function getAllDecks() {
  console.log("getAllDecks");
  decksList.value = await deckApi.getALlDecks();
}

async function createDeck() {
  await deckApi.createDeck({
    name: "df",
  });
}

getAllDecks();
</script>

<style scoped lang="sass">
.list-decks
  align-content: center

.decks-container
  margin-left: 150px
  margin-right: 150px


.menu-btn
  margin: 5px 5px 5px 33px

.menuDeck
  background: #31ccec
  display: flex
  margin: 10px 120px 10px 120px
  border-radius: 30px
</style>
