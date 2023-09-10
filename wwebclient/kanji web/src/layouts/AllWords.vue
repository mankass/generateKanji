<template>
  <q-layout view="hHh Lpr fFf">

    <q-header elevated  >
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
          <q-item href="http://localhost:9000/#/all" clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="inbox" />
            </q-item-section>

            <q-item-section>
              ALl words
            </q-item-section>
          </q-item>

          <q-item  href="http://localhost:9000/#/" active clickable v-ripple>
            <q-item-section avatar >
              <q-icon name="star" />
            </q-item-section>

            <q-item-section>
              Home
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="send" />
            </q-item-section>

            <q-item-section>
              Send
            </q-item-section>
          </q-item>

          <q-item clickable v-ripple>
            <q-item-section avatar>
              <q-icon name="drafts" />
            </q-item-section>

            <q-item-section>
              Drafts
            </q-item-section>
          </q-item>
        </q-list>
      </q-scroll-area>
    </q-drawer>


    <q-page-container>
      <div class="q-pa-md">
        <div class="q-a-lg-lgd">
          <q-table
            flat bordered
            title="Words"
            :rows="wordsList"
            :columns="columnsTest"
            row-key="name"
            dark
            color="amber"
          ></q-table>
        </div>
      </div>


      <router-view/>
    </q-page-container>

  </q-layout>
</template>

<script lang="ts" setup>
import {ref} from 'vue'
import {APIApi, WordData} from "../../../generated/.penapi-generator";


const api = new APIApi()
const drawer = ref(false)
const wordsList = ref<WordData[]>();
const columnsTest = [
  {
    name: 'word',
    label: 'Word',
    align: 'left',
    field: 'word',
    sortable: true
  },
  {name: 'translate', align: 'center', label: 'Translate', field: 'translate', sortable: true},
  {name: 'transcription', align: 'center', label: 'Transcription', field: 'transcription', sortable: true}
]


async function getAllWords() {
  console.log('starrt')
  wordsList.value = await api.getAllWords();
}

getAllWords()
</script>
