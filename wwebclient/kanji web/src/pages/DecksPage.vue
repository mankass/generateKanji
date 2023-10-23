<template>
  <q-page-container class="q-page-container">
    <q-list class="q-ma-lg">
      <q-card v-for="deck in decksList" :key="id" class="q-ma-md">
        {{ deck.name }}
        <q-card-actions>
          <q-btn
            color="grey"
            round
            flat
            dense
            :icon="expanded ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"
            @click="expanded = !expanded"
          ></q-btn>
        </q-card-actions>

        <q-slide-transition>
          <div v-show="expanded">
            <q-separator></q-separator>
            <q-card-section class="text-subtitle2">
              <q-card v-for="word in deck.listWords">
                <div class="q-ma-xs">
                  {{ word.word }} - {{ word.translate }}
                </div>
              </q-card>
            </q-card-section>
          </div>
        </q-slide-transition>
      </q-card>
    </q-list>
  </q-page-container>
</template>

<script lang="ts" setup>
import {Configuration, DeckAPIApi, DeckData,} from "../../../generated";
import {ref} from "vue";

const deckApi = new DeckAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);
const decksList = ref<DeckData[]>();
const expanded = ref(false);

async function getAllDecks() {
  console.log("getAllDecks");
  decksList.value = await deckApi.getALlDecks();
}

getAllDecks();
</script>

<style scoped></style>
