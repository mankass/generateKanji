<template>
  <q-page-container class="q-page-container">
    <div class="row">
      <span class="loader"></span>
      <div class="deck-container">
        <div class="row menuDeck q-mt-lg-lg">
          <q-btn class="menu-btn" @click="showAddDeck = true" color="orange"
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

      <div>
        <q-table
          card-class="leaderBoard-container"
          :rows-per-page-options="[15]"
          flat
          class="leaderboard-list"
          bordered
          title="Leaderboard"
          :rows="deckLeaderBoard"
          :columns="columnsTest"
          row-key="name"
          hide-pagination
          color="amber"
          on-row-click=""
        >
        </q-table>
      </div>
    </div>

    <q-dialog v-model="showAddDeck">
      <app-create-deck
        @needRefresh="getAllDecks"
        @choose-deckName="createDeck"
      ></app-create-deck>
    </q-dialog>
  </q-page-container>
</template>

<script lang="ts" setup>
import {
  APIApi,
  Configuration,
  DeckAPIApi,
  DeckView,
  LeaderboardDeckView,
} from "../../../generated";
import {ref} from "vue";
import AppDeck from "components/AppDeck.vue";
import AddWord from "components/AppCreateDeck.vue.vue";
import AppCreateDeck from "components/AppCreateDeck.vue";

let reload = ref<boolean>(false);

function onRowClick() {
  console.log("click me");
}

const showAddDeck = ref<boolean>(false);

const columnsTest = [
  {
    name: "slot",
    label: "Slot",
    align: "left",
    field: "slot",
    sortable: true,
  },
  {
    name: "deckName",
    label: "Name",
    align: "left",
    field: "deckName",
  },
  {
    name: "Percent",
    label: "Percent",
    align: "left",
    field: "percentCorrect",
    sortable: true,
  },
  {
    name: "usersUsing",
    label: "usersUsing",
    align: "left",
    field: "usersUsing",
    sortable: true,
  },
];

const api = new APIApi();
const deckLeaderBoard = ref<LeaderboardDeckView[]>();

const deckApi = new DeckAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);
const decksList = ref<DeckView[]>();

async function getAllDecks() {
  decksList.value = await deckApi.getALlDecks();
}

async function getLeaderboardDecks() {
  deckLeaderBoard.value = await api.getDeckLeaderboard();
}

async function createDeck(name: string) {
  await deckApi.createDeck({
    name: name,
  });
  await getAllDecks();
}

getAllDecks();
getLeaderboardDecks();
</script>

<style scoped lang="sass">
.list-decks
  align-content: center

.decks-container
  margin-left: 150px
  margin-right: 150px
  min-width: 900px


.menu-btn
  margin: 5px 5px 5px 33px


.leaderBoard-container
  margin-top: 30px
  color: #033d75
  border-radius: 54px
  border: #26a69a
  font-family: 'Ubuntu', 'Microsoft JhengHei', Arial, sans-serif
  letter-spacing: 1px
  background: #E8CBC0
  background: -webkit-linear-gradient(to left, #E8CBC0, #636FA4)
  background: linear-gradient(to top, #E8CBC0, #636FA4)
  text-align: center

  &::before
    content: ''
    height: 100%
    display: inline-block
    vertical-align: middle

.menuDeck
  background: #31ccec
  display: flex
  margin: 10px 120px 10px 120px
  border-radius: 30px

*
  outline: none
  user-select: none

body, html
  margin: 0
  background: #ddd
  height: 100%

.leaderboard
  background-color: #123
  color: #eee
  width: 300px
  border-radius: 5px
  top: 20%
  box-shadow: 2px 5px 15px rgba(#123, .5)
  text-align: left
  display: inline-block
  vertical-align: middle

  title
    padding: 15px 0
    margin: 0
    text-align: center

  p
    margin: 3px 10px
    text-align: right
    color: #567
</style>
<style lang="css" scoped>
.loader {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  display: inline-block;
  border-top: 4px solid #fff;
  border-right: 4px solid transparent;
  box-sizing: border-box;
  animation: rotation 1s linear infinite;
}

.loader::after {
  content: "";
  box-sizing: border-box;
  position: absolute;
  left: 0;
  top: 0;
  width: 48px;
  height: 48px;
  border-radius: 50%;
  border-bottom: 4px solid #ff3d00;
  border-left: 4px solid transparent;
}

@keyframes rotation {
  0% {
    transform: rotate(0deg);
  }
  100% {
    transform: rotate(360deg);
  }
}
</style>
