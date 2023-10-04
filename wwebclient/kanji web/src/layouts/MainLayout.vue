<template>
  <div>
    <q-layout class="back">
      <q-header elevated>
        <q-toolbar>
          <q-btn flat @click="drawer = !drawer" round dense icon="menu"></q-btn>
          <q-toolbar-title>KanjiLearner</q-toolbar-title>
        </q-toolbar>
      </q-header>

      <q-drawer
        v-model="drawer"
        show-if-above
        :width="200"
        bordered
        :breakpoint="500"
        class="bg-grey"
      >
        <q-scroll-area class="fit">
          <q-list padding class="menu-list">
            <q-item href="http://localhost:9000/#/allw" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="inbox" />
              </q-item-section>

              <q-item-section> All words </q-item-section>
            </q-item>

            <q-item href="http://localhost:9000/#/" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="star" />
              </q-item-section>

              <q-item-section> Home </q-item-section>
            </q-item>

            <q-item href="http://localhost:9000/#/generator" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="send" />
              </q-item-section>

              <q-item-section> Generator </q-item-section>
            </q-item>

            <q-item href="http://localhost:9000/#/decks" clickable v-ripple>
              <q-item-section avatar>
                <q-icon name="folder_copy" />
              </q-item-section>

              <q-item-section> Decks </q-item-section>
            </q-item>
          </q-list>
        </q-scroll-area>
      </q-drawer>

      <router-view />
    </q-layout>
  </div>
</template>

<script lang="ts" setup>
import { defineComponent, Ref, ref } from "vue";
import EssentialLink from "components/Header.vue";
import { APIApi, Configuration, WordData } from "../../../generated/";
import { safe } from "../../../generated/client-sdk";

const api = new APIApi();
const wordsList = ref<WordData[]>();
const drawer = ref(true);
const miniState = ref(true);
async function getWords() {
  await safe(
    async () => {
      try {
        console.log(wordsList.value);
        console.log("console");
        wordsList.value = await api.getAllWords();
        console.log(wordsList.value);
      } finally {
        console.log("final");
      }
    },
    { loading: false }
  );
}
</script>

<style lang="sass" scoped>


.back
  background: rgba(127, 255, 212, 0.22)
</style>
