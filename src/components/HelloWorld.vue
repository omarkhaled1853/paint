<template>
  <div class="appaint" style="border:solid black 2px; width:950px; background-color:#404340 ;">
  <div class="operations" >
    <button>open <i class="fa-solid fa-folder-open"></i></button>
    <button>Save <i class="fa-solid fa-floppy-disk"></i></button>
    <button>Undo <i class="fa-solid fa-arrow-rotate-left"></i></button>
    <button>Redo <i class="fa-solid fa-rotate-right"></i></button>
    <button>Delete <i class="fa-solid fa-eraser"></i></button>
    <button>clear <i class="fa-solid fa-trash"></i></button>
    <button>copy <i class="fa-solid fa-copy"></i></button>
  </div>
  <div class="paint">
    <div class="bordshaps" >
    <div class="shaps">
      <div class="color" >
        <div class="block">
        <div class="fillcolor" >
        <color-picker  v-model:pureColor="pureColor" v-model:gradientColor="gradientColor"/>
      </div>
      <label>fill color</label>
      </div>
      <div class="block">
      <div class="fillcolor" >
        <color-picker v-model:pureColor="pureColor" v-model:gradientColor="gradientColor"/>
      </div>
      <label >edge color</label>
    </div>
      </div>
      <div class="block">
     <button ><div class="square"></div></button>
     <label >square</label>
    </div>
      <div class="block">
     <button ><div class="rectengle"></div></button>
     <label >rectengle</label>
    </div>
      <div class="block">
        <button ><div class="circle"></div></button>
     <label >circle</label>
    </div>
      <div class="block">
     <button ><div class="triangle-up"></div></button>
     <label >triangle</label>
    </div>
    <div class="block">
      <button ><div class="oval"></div></button>
      <label >ellips</label>
     </div>
    <div class="block">
      <button style="width:30px;"><i class="fa-solid fa-lines-leaning"></i></button>
      <label >line</label>
     </div>
    </div>
  </div>
    <div class="bord" style="border: solid black 2px; border-radius: 10px; background-color:#ffffff ">
  <v-stage  :config="configKonva" >
    <v-layer>
      <v-circle :config="configCircle"></v-circle>
    </v-layer>
  </v-stage>
</div>
  
</div>
</div>
</template>

<script>
import { ref } from "vue";
import { ColorInputWithoutInstance } from "tinycolor2";

export default {
  name: 'HelloWorld',
  data() {
    return {
      value:null ,
      colorfil:null,
      configKonva: {
        width: 800,
        height: 600,
      }
    }
  },
  methods:{
    setup() {
       const pureColor = ref<ColorInputWithoutInstance>("red");
       return { pureColor }
     },
  }
  
};
</script>

<style>
.paint{
  display: flex;
  justify-content: center;
  justify-items: center;
  
}
.operations{
  width:600px;
  border: #555 solid  ;
  border-color: #555;
  background-color: rgb(111, 125, 131);
  border-radius:20px;
  display: flex;
  gap: 20px;
  align-content: center;
  margin:10px;
  margin:  2px auto;
  justify-content: center;
  justify-items: center;

}
.bordshaps{
  height: 250px;
  margin: 3px;
  padding-left: 1px;
  padding-right: 1px;
  display: flex;
  flex-direction: column;
  border: #555 solid  ;
  border-color: #555;
  background-color: rgb(111, 125, 131);
  border-radius:20px;
  margin:   auto 2px;
  justify-items: center;
  justify-content: center;
  gap: 5px;
  
}
.shaps{
  margin-right: 3px;
  display: flex;
  flex-direction: column;
  gap: 5px;
  
} 
label{
  opacity: 0.2;
  color: aliceblue;
  
}
.block{
  display: flex;
  flex-direction: row;
  gap: 5px;
}
button:hover {
  cursor: pointer;
}
label:hover{
  opacity: 100;
  color: aliceblue;

}
.square {
  height: 10px;
  width: 10px;
  border: black solid;
}
.rectengle {
  height: 5px;
  width: 10px;
  border: black solid;
}
.circle {
  height: 10px;
  width: 10px;
  border: black solid;
  border-radius: 50%;
}
.circle1{
  height: 50px;
  width: 50px;
  border: black solid;
  border-radius: 50%;
}
.oval {
  height: 5px;
  width: 10px;
  border: black solid;
  
  border-radius: 50%;
}
.triangle-up {
	width: 0;
	height: 0;
	border-left: 8px solid transparent;
	border-right: 8px solid transparent;
	border-bottom: 14px solid #555;
}
.line{
	border-left: 1px solid transparent;
	border-bottom:18px solid #555;
}
.color{
  display: flex;
  flex-direction: column;
  gap: 5px;
  margin-bottom: 10px;

}
.fillcolor{
  height: 24px;
  width: 23px;
  border: black solid;
  border-radius: 49%;
  overflow: hidden;

}

</style>
