<template>
  <div class="appaint" style="border:solid black 2px; width:1130px; background-color:#404340 ;">
    <div class="header">
  <div class="operations" >
    <button style="background-color:rgb(111, 125, 131)">open <i class="fa-solid fa-folder-open"></i></button>
    <button style="background-color:rgb(111, 125, 131)">Save <i class="fa-solid fa-floppy-disk"></i></button>
    <button style="background-color:rgb(111, 125, 131)">Undo <i class="fa-solid fa-arrow-rotate-left"></i></button>
    <button style="background-color:rgb(111, 125, 131)">Redo <i class="fa-solid fa-rotate-right"></i></button>
    <button  style="background-color:rgb(111, 125, 131)" @click="del()">Delete <i class="fa-solid fa-eraser"></i></button>
    <button style="background-color:rgb(111, 125, 131)"  @click="clr()">clear <i class="fa-solid fa-trash"></i></button>
    <button style="background-color:rgb(111, 125, 131)" >copy <i class="fa-solid fa-copy"></i></button>
  </div>
  <div class="clorfil" >
    <div class="block">
      <div class="fillcolor">
        <color-picker v-model:pureColor="pureColor" v-model:gradientColor="gradientColor"/>
    </div>
      <button @click="fill()" style="background-color:rgb(111, 125, 131)">fill<i class="fa-solid fa-fill-drip"></i></button>
      <button @click="edge()" style="background-color:rgb(111, 125, 131)">border<i class="fa-solid fa-paintbrush"></i></button>
    
  </div>
  </div>
</div>
 
  <div class="paint">
    <div class="bordshaps" >
    <div class="shaps">
      <div class="block">
     <button @click="square()" ><div class="square"></div></button>
     <label >square</label>
    </div>
      <div class="block">
     <button @click="rect()" ><div class="rectengle"></div></button>
     <label >rectengle</label>
    </div>
      <div class="block">
        <button @click="circle()" ><div class="circle"></div></button>
     <label >circle</label>
    </div>
      <div class="block">
     <button @click="triangle()" ><div class="triangle-up"></div></button>
     <label >triangle</label>
    </div>
    <div class="block">
      <button @click="ellipse()"><div class="oval"></div></button>
      <label >ellips</label>
     </div>
    <div class="block">
      <button @click="line()" style="width:30px;"><i class="fa-solid fa-lines-leaning"></i></button>
      <label >line</label>
     </div>
    </div>
  </div>
    <div class="bord" style="border: solid black 2px; border-radius: 10px; background-color:#ffffff;margin-left:2px ">
  <v-stage :config="configKonva" ref="stage" @mousedown="startDrawing" @mouseup="stopDrawing" @mousemove="draw" >
    <v-layer>
      <!-- drawing rectangles -->
      <v-rect
       v-for="(rect, index) in rectangles"
        :key="index"
        :config="{
          x: rect.x,
          y: rect.y,
          width: rect.width,
          height: rect.height,

          fill: selectedColor, // Use the selected fill color
          stroke: selectedColor, // Use the selected edge color
          fill: rect.fill, // Use the selected fill color
          stroke: rect.stroke, // Use the selected edge color
          strokeWidth: 2 ,
          draggable:true,
       }"
        @click="shapeClicked('rect', index) "
      ></v-rect>
     <!-- drawing circles -->
     <v-circle
      v-for="(circle, index) in circles"
        :key="index"
        :config="{
          x: circle.x,
          y: circle.y,
          radius:circle.radius,

          fill:selectedColor2, // Use the selected fill color
          stroke: selectedColor9, // Use the selected edge color
          fill:circle.fill,
          stroke:circle.stroke,
          strokeWidth: 2,
          draggable:true,
        }"
         @click="shapeClicked('circle', index)"
     >

     </v-circle>
     <!-- drawing ellipses -->
     <v-ellipse
        v-for="(ellipse, index) in ellipses"
        :key="index"
        :config="{
          x: ellipse.x,
          y: ellipse.y,
          radiusX:ellipse.radiusX,
          radiusY:ellipse.radiusY,

          fill: selectedColor3, // Use the selected fill color
          stroke: selectedColor6, // Use the selected edge color
          fill: ellipse.fill, // Use the selected fill color
          stroke: ellipse.stroke, // Use the selected edge color
          strokeWidth: 2,
          draggable:true,
        }"
       @click="shapeClicked('ellipse', index)"
     >

     </v-ellipse>
     <!-- drawing line segment -->
     <v-line
       v-for="(line, index) in lines"
        :key="index"
        :config="{
          points:line.points,
          stroke: line.stroke,
          strokeWidth: 2,
          draggable:true,
        }"
         @click="shapeClicked('line', index)"
     >

     </v-line>
  <!-- square -->
  <v-regular-polygon
   v-for="(square, index) in squares"
        :key="index"
        :config="{
          x: square.x,
          y: square.y,
          sides:4,
          radius:square.radius,

          fill: selectedColor4, // Use the selected fill color
          stroke: selectedColor8, // Use the selected edge color
          fill: square.fill, // Use the selected fill color
          stroke: square.stroke, // Use the selected edge color
          strokeWidth: 2,
          draggable:true,
        }"
      @click="shapeClicked('square', index)"
  >

  </v-regular-polygon>
  <v-regular-polygon
   v-for="(triangle, index) in triangles"
        :key="index"
        :config="{
          x: triangle.x,
          y: triangle.y,
          sides:3,
          radius:triangle.radius,

          fill: selectedColor5, 
          strok:selectedColor10, 
          fill: triangle.fill, 
          stroke: triangle.stroke, 
          strokeWidth: 2,
          draggable:true,
        }"
      @click="shapeClicked('triangle', index)"
  >

  </v-regular-polygon>
  

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
      value: null,
      selectedColor: '',
      selectedColor2: '',
      selectedColor3: '',
      selectedColor4: 'white',
      selectedColor5: 'white',
      selectedColor9: 'black',
      selectedColor6: 'black',
      selectedColor8: 'black',
      selectedColor1: 'black',
      configKonva: {
        width: 1000,
        height: 800,
      },
      pureColor: '#ffffff', // Selected fill color
      pureColor2: '#000000', // Selected edge color
      isdraw: false,
      rectangles:[],
      squares:[],
      lines:[],
      circles:[],
      ellipses:[],
      triangles:[],
      rectangle:false,
      circ:false,
      ellips:false,
      lin:false,
      sqrt:false,
      tria:false,
      delete:false,
      mov:false,
      cofill:false,
      coledge:false,
      currentShape:null,
    
    };
  },
  methods: {
    setup() {
      const pureColor = ref<ColorInputWithoutInstance>("red");
      return{pureColor}
    },
    rect(){
      this.rectangle=true;
       this.circ=false;
      this.ellips=false;
      this.lin=false;
      this.sqrt=false;
      this.tria=false;
    },
    ellipse()
    {
       this.rectangle=false;
       this.circ=false;
      this.ellips=true;
      this.lin=false;
      this.sqrt=false;
      this.tria=false;
    }, 
    circle()
    {
     this.rectangle=false;
       this.circ=true;
      this.ellips=false;
      this.lin=false;
      this.sqrt=false;
      this.tria=false;
    },
    line()
    {
         this.rectangle=false;
       this.circ=false;
      this.ellips=false;
      this.lin=true;
      this.sqrt=false;
      this.tria=false;
    },
    square()
    {
      this.rectangle=false;
       this.circ=false;
      this.ellips=false;
      this.lin=false;
      this.sqrt=true;
      this.tria=false;
    },
    triangle()
    {
        this.rectangle=false;
       this.circ=false;
      this.ellips=false;
      this.lin=false;
      this.sqrt=false;
      this.tria=true;
    },
    fill(){
      this.cofill=true;
    },
    edge(){
      this.coledge=true;
    },
    clr()
    {
      this.circles=[];
      this.lines=[];
      this.squares=[];
      this.rectangles=[];
      this.triangles=[];
      this.ellipses=[];
    
    },
     shapeClicked(type, index) {
      if(type==='triangle')
     {
        if(this.delete)
        {
              this.triangles.splice(index,1);
              this.delete=false;
        }

        if (this.cofill) {
        this.selectedColor5= this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
       }
       if(this.coledge){
          this.triangles[index].stroke = this.pureColor;
          this.selectedColor10= this.pureColor;
            this.coledge=false;
            this.pureColor = '#ffffff';
       
            }

       if (this.cofill) {
        let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
        this.selectedColor2= selectedFillColor
        this.triangles[index].fill = selectedFillColor; // Assign the selected fill color to the fill property of the shape object
        selectedFillColor=this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
        
                 }
             if(this.coledge){
              let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.triangles[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                
       
          }     
       
       /*********************************************************************** */
        
     }
     else if(type==='circle')
     {
          if(this.delete)
        {
         
              this.circles.splice(index,1);
                this.delete=false;
        }
        if (this.cofill) {
        let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
        this.circles[index].fill = selectedFillColor; // Assign the selected fill color to the fill property of the shape object
        selectedFillColor=this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
        
                 }

        if(this.coledge){
          this.circles[index].stroke = this.pureColor;
          this.selectedColor9= this.pureColor;
            this.coledge=false;
            this.pureColor = '#ffffff';
       
            }
                if(this.coledge){
             let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.circles[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                
        
       
     }  
       
     }
     else if(type==='line')
     {
             if(this.delete)
        {
         
              this.lines.splice(index,1);
                this.delete=false;
        }
        if(this.coledge){

          this.lines[index].stroke = this.pureColor;
            this.coledge=false;
       
            }
       
             let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.lines[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                
        }
     }
     /************************************************************ */
     else if(type==='rect')
     {
             if(this.delete)
        {
         
              this.rectangles.splice(index,1);
                this.delete=false;
        }
        if (this.cofill) {
        let selectedFillColor = this.pureColor; // Store the selected fill color in a variable
        this.rectangles[index].fill = selectedFillColor; // Assign the selected fill color to the fill property of the shape object
        //selectedFillColor=this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
        
                 }
        if(this.coledge){

          this.rectangles[index].stroke = this.pureColor;
          this.selectedColor1=this.pureColor;
            this.coledge=false;
            this.pureColor = '#ffffff';
       
            }
    }
     else if(type==='ellipse')
     {
             if(this.delete)
             let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.rectangles[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                
        
       
     }
     }
     /*************************************************************** */
      else if(type==='ellipse') {
                if(this.delete)
        {
         
              this.ellipses.splice(index,1);
                this.delete=false;
        }

        if (this.cofill) {
        this.selectedColor3= this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
        
                 }
        if(this.coledge){
            this.ellipses[index].stroke = this.pureColor;
            this.selectedColor6= this.pureColor
            this.coledge=false;
            this.pureColor = '#ffffff';
          }
          if (this.cofill) {
      let selectedFillColor = this.pureColor;
      this.ellipses[index].fill = selectedFillColor;
      this.cofill = false;
      this.pureColor = '#ffffff';
    }

        if(this.coledge){
           let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.ellipses[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                
       
     }
        }
        /**************************************************** */
     else if(type==='square')
     {

     // this.squares[index].fill = this.selectedColor;
               if(this.delete)
        {
         
              this.squares.splice(index,1);
                this.delete=false;
        }

        if (this.cofill) {
        this.selectedColor4= this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
        }
        if(this.coledge){
            this.squares[index].stroke = this.pureColor;
            this.selectedColor8= this.pureColor
            this.coledge=false;
            this.pureColor = '#ffffff';
          }
       
     }

  },
      if (this.cofill) {
        let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
        this.selectedColor2= selectedFillColor
        this.squares[index].fill = selectedFillColor; // Assign the selected fill color to the fill property of the shape object
        selectedFillColor=this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
        
                 }
        if(this.coledge)
        {
                  let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.squares[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                
        }         
       
       
     }

    },
 

    del()
    {
          this.delete=true;
    },
    move()
    {
      this.mov=true;
    },
    
    
      startDrawing() {
     if(this.rectangle===true)
      {
          this.isdraw = true;
      const stage = this.$refs.stage.getStage();
      if (stage) {
        const position = stage.getPointerPosition();
        if (position) {
          this.currentShape = {
            x: position.x,
            y: position.y,
             fill:this.pureColor,
            stroke:this.pureColor2,
            width: 0,
            height: 0,
          };
        }
      }
      }
      else if(this.circ===true){
                this.isdraw = true;
      const stage = this.$refs.stage.getStage();
      if (stage) {
        const position = stage.getPointerPosition();
        if (position) {
          this.currentShape = {
            x: position.x,
            y: position.y,
            fill:this.pureColor,
            stroke:this.pureColor2,
            radius: 0,
          };
        }
      }
      }
      else if(this.ellips===true)
      {
           this.isdraw = true;
          const stage = this.$refs.stage.getStage();
          if (stage) {
            const position = stage.getPointerPosition();
            if (position) {
              this.currentShape = {
                x: position.x,
                y: position.y,
                 fill:this.pureColor,
            stroke:this.pureColor2,
              radiusX: 0,
              radiusY:0
              };
            }
          }
      }
      else if(this.lin===true)
      {
              this.isdraw = true;
          const stage = this.$refs.stage.getStage();
          if (stage) {
            const position = stage.getPointerPosition();
            if (position) {
              this.currentShape = {
                
            stroke:this.pureColor2,
             points: [position.x, position.y]
              };
            }
          }
      }
      else if(this.sqrt===true)
      {
            this.isdraw = true;
          const stage = this.$refs.stage.getStage();
          if (stage) {
            const position = stage.getPointerPosition();
            if (position) {
              this.currentShape = {
                x: position.x,
                y: position.y,
                fill:this.pureColor,
            stroke:this.pureColor2,
                radius:0
              };
            }
          }
      }
      else if(this.tria)
      {
          this.isdraw = true;
          const stage = this.$refs.stage.getStage();
          if (stage) {
            const position = stage.getPointerPosition();
            if (position) {
              this.currentShape = {
               x: position.x,
                y: position.y,
                fill:this.pureColor,
            stroke:this.pureColor2,
                radius:0
              };
            }
          }
      }
  
     
      
    
    },
    stopDrawing() {
     
          if (this.isdraw) {
        this.isdraw = false;
        console.log("xxxxx");
        if(this.circ)
        this.circles.push({ ...this.currentShape });
      else if(this.rectangle)
      this.rectangles.push({...this.currentShape});
      else if(this.ellips)
            this.ellipses.push({...this.currentShape});
      else if(this.lin)
           this.lines.push({...this.currentShape});
      else if(this.sqrt)
           this.squares.push({...this.currentShape});
      else if(this.tria)
            this.triangles.push({...this.currentShape});    



        this.currentShape = null;
        this.circ=false;
        this.ellips=false;
        this.rectangle=false;
        this.lin=false;
        this.sqrt=false;
        this.tria=false;
        this.mov = false;
      }
     
      
      
    
    },
    draw() {
      if(this.rectangle)
      {
            if (this.isdraw) {
        const stage = this.$refs.stage.getStage();
        if (stage) {
          const position = stage.getPointerPosition();
          if (position) {
            this.currentShape.width = position.x - this.currentShape.x;
            this.currentShape.height = position.y - this.currentShape.y;
          }
        }
      }
      }
    //--------------------------------------------------------------------------------
      else if(this.circ)
      {
          if (this.isdraw) {
        const stage = this.$refs.stage.getStage();
        if (stage) {
          const position = stage.getPointerPosition();
          if (position) {
            this.currentShape.radius = Math.sqrt(
                Math.pow(position.x - this.currentShape.x, 2) + Math.pow(position.y - this.currentShape.y, 2));
          }
        }
      }
      }
      //************************************************************************************************** */
      else if(this.ellips)
      {
            if (this.isdraw) {
        const stage = this.$refs.stage.getStage();
        if (stage) {
          const position = stage.getPointerPosition();
          if (position) {
            this.currentShape.radiusX = Math.abs(position.x - this.currentShape.x);
            this.currentShape.radiusY=Math.abs(position.y - this.currentShape.y);
          }
        }
      }
      }
      //******************************************** */
      else if(this.lin)
      {
            if (this.isdraw) {
        const stage = this.$refs.stage.getStage();
        if (stage) {
          const position = stage.getPointerPosition();
          if (position) {
             this.currentShape.points[2] = position.x;
          this.currentShape.points[3] = position.y;
           
          }
        }
      }
      }
      //******************************************** */
      else if(this.sqrt)
      {
            if (this.isdraw) {
        const stage = this.$refs.stage.getStage();
        if (stage) {
          const position = stage.getPointerPosition();
          if (position) {
           
            this.currentShape.radius=Math.sqrt(
                Math.pow(position.x - this.currentShape.x, 2) + Math.pow(position.y - this.currentShape.y, 2));
          }
        }
      }
      }
      //************************************ **/
      else if(this.tria)
      {
             if (this.isdraw) {
        const stage = this.$refs.stage.getStage();
        if (stage) {
          const position = stage.getPointerPosition();
          if (position) {
           
            this.currentShape.radius=Math.sqrt(
                Math.pow(position.x - this.currentShape.x, 2) + Math.pow(position.y - this.currentShape.y, 2));
          }
        }
      }
      }
    
    },
  }  ,
};
</script>





<style>
.paint{
  display: flex;
  justify-content: center;
  justify-items: center;
  
}
.header{
  display: flex;
  flex-direction: row;
  gap:0px;

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
.clorfil{
  margin-right: 30px;
  width:180px;
  border: rgb(7, 5, 5) solid  ;
  border-color: #555;
  background-color: rgb(111, 125, 131);
  border-radius:20px;
  
}
.bordshaps{
  height: 180px;
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
