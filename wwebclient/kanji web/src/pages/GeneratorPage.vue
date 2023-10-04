<template>
  <q-page-container>
    <div class="q-pa-md row items-start q-gutter-md">
      <div class="col-3">


        <q-card class="my-card q-ma-md bg-secondary text-white">
          <q-card-section>
            <div> Generate today</div>
          </q-card-section>
          <q-separator dark></q-separator>
          <q-card-actions class="q-pa-sm-md">
            <q-btn class="button" @click="generateToday" >Download</q-btn>
          </q-card-actions>
        </q-card>

        <q-card class="my-card q-ma-md bg-secondary text-white">
          <q-card-section>
            <div>  Generate by difficult</div>
          </q-card-section>
          <q-separator dark></q-separator>
          <q-card-actions class="q-pa-sm-md">
            <q-btn class="button" >Download</q-btn>
          </q-card-actions>
        </q-card>


      </div>

      <div class="col">
        <q-card class="my-card q-ma-md bg-secondary text-white">
          <q-card-section>
            <div>Generate by deck</div>
          </q-card-section>
          <q-separator dark></q-separator>
          <q-card-actions class="q-pa-sm-md">
            <q-btn class="button" >Download</q-btn>
          </q-card-actions>
        </q-card>
        <q-card class="my-card q-ma-md bg-secondary text-white">
          <q-card-section>
            <div>Generate ALl</div>
          </q-card-section>
          <q-separator dark></q-separator>
          <q-card-actions class="q-pa-sm-md">
            <q-btn class="button" >Download</q-btn>
          </q-card-actions>
        </q-card>

      </div>

      <div class=""></div>
    </div>
  </q-page-container>
</template>

<script lang="ts" setup>

import {APIApi, DeckAPIApi} from "../../../generated";

const api = new APIApi();
const deckAPIApi = new DeckAPIApi();


  function generateToday(){
    api.generateToday()
  }

  function generateByDifficult(){

  }

  function generateByDeck(){

  }

  function generateAll(){

  }







</script>

<style lang="sass" scoped>

.my-card
  width: 180px
  height: 200px
.button
  background: #72a407

</style>
