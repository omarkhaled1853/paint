<template>
    <div class="appaint" style="border:solid black 2px; width:1130px; background-color:#404340 ;">
      <div class="header">
    <div class="operations" >
      <button style="background-color:rgb(111, 125, 131)">open <i class="fa-solid fa-folder-open"></i></button>
      <button style="background-color:rgb(111, 125, 131)" @click="save()">Save <i class="fa-solid fa-floppy-disk"></i></button>
      <button style="background-color:rgb(111, 125, 131)">Undo <i class="fa-solid fa-arrow-rotate-left"></i></button>
      <button style="background-color:rgb(111, 125, 131)">Redo <i class="fa-solid fa-rotate-right"></i></button>
      <button  style="background-color:rgb(111, 125, 131)" @click="del()">Delete <i class="fa-solid fa-eraser"></i></button>
      <button style="background-color:rgb(111, 125, 131)"  @click="clr()">clear <i class="fa-solid fa-trash"></i></button>
      <button style="background-color:rgb(111, 125, 131)" >copy <i class="fa-solid fa-copy"></i></button>
      <button for="userInput"  style="background-color:rgb(111, 125, 131)" @click="resize() ">resize</button>
      <input type="text" id="userInput" style="border:solid black ;">
      
  
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
    <v-stage :config="configKonva" ref="stage" @mousedown="startDrawing" @mouseup="stopDrawing" @mousemove="draw" 
       >
      <v-layer>
        <!-- drawing rectangles -->
        <v-rect
         v-for="(rect, index) in rectangles"
          :key="index"
          :config="{ 
            type:'rectangle',
            x:  rect.x,
            y: rect.y,
            width: rect.width,
            height: rect.height,
            fill: rect.fill, // Use the selected fill color
            stroke: rect.stroke, // Use the selected edge color
            strokeWidth: 2 ,
            draggable:true,
             
         }"
           
          @click="shapeClicked('rect', index) "
         @dragend="newpo('rect', index, $event)"   
        
          
        ></v-rect>
       <!-- drawing circles -->
       <v-circle
        v-for="(circle, index) in circles"
          :key="index"
          :config="{
            type:'circle',
            x: circle.x,
            y: circle.y,
            radius:circle.radius,
            fill:circle.fill,
            stroke:circle.stroke,
            strokeWidth: 2,
            draggable:true,
          }"
           @click="shapeClicked('circle', index)"
           @dragend="newpo('circle', index, $event)"     >
  
       </v-circle>
       <!-- drawing ellipses -->
       <v-ellipse
          v-for="(ellipse, index) in ellipses"
          :key="index"
          :config="{
            type:'ellipse',
            x: ellipse.x,
            y: ellipse.y,
            radiusX:ellipse.radiusX,
            radiusY:ellipse.radiusY,
            fill: ellipse.fill, // Use the selected fill color
            stroke: ellipse.stroke, // Use the selected edge color
            strokeWidth: 2,
            draggable:true,
          }"
         @click="shapeClicked('ellipse', index)"
         @dragend="newpo('ellipse', index, $event)"   
       >
  
       </v-ellipse>
       <!-- drawing line segment -->
       <v-line
         v-for="(line, index) in lines"
          :key="index"
          :config="{
            type:'line',
            points:line.points,
            stroke: line.stroke,
            strokeWidth: 2,
            draggable:true,
          }"
           @click="shapeClicked('line', index)"
           @dragend="newpo('line', index, $event)"   
       >
  
       </v-line>
    <!-- square -->
    <v-rect
     v-for="(square, index) in squares"
          :key="index"
          :config="{
            type:'square',
             x: square.x,
            y: square.y,
            width: square.width,
            height: square.height,
            fill: square.fill, // Use the selected fill color
            stroke: square.stroke, // Use the selected edge color
            strokeWidth: 2 ,
            draggable:true,
          }"
        @click="shapeClicked('square', index)"
        @dragend="newpo('square', index, $event)"   
    >
  
    </v-rect>
    <v-regular-polygon
     v-for="(triangle, index) in triangles"
          :key="index"
          :config="{
            type:'triangle',
            x: triangle.x,
            y: triangle.y,
            sides:3,
            radius:triangle.radius,
            fill: triangle.fill, 
            stroke: triangle.stroke, 
            strokeWidth: 2,
            draggable:true,
          }"
        @click="shapeClicked('triangle', index)"
        @dragend="newpo('triangle', index, $event)"   
    >
  
    </v-regular-polygon>
      
  
      </v-layer>
    </v-stage>
  </div>
    
  </div>
  </div>
  </template>
  
  <script>
  //import Konva from 'konva';
  
  import { ref } from "vue";
  import { ColorInputWithoutInstance } from "tinycolor2";
  
  export default  {
    name: 'HelloWorld',
    data() {
      return {
        value: null,
        selectedColor: '',
        selectedColor2: '',
        selectedColor3: '',
        selectedColor4: '',
        selectedColor5: '',
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
        bru:false,
        delete:false,
        mov:false,
        cofill:false,
        coledge:false,
        currentShape:null,
        shapes:[],
        selectedShapeName: '',
        resiz:false,
        userInput:0,
        sav:false,
        path:"D:\\test.json" 
      };
    },
    methods: {
      setup() {
        const pureColor = ref<ColorInputWithoutInstance>("red");
        return{pureColor}
      },
      save()
      {
          this.saving();
      },
      
  newpo(type, index,e) {
     var v=0;
    if (type === 'circle') {
     
      for(let i=0;i<this.shapes.length;i++){
        if(this.shapes[i]===this.circles[index])
        {
             v=i;
             break;
        }
      }
      this.circles[index].x=e.target.x();
      this.circles[index].y=e.target.y();
      this.shapes[v]=this.circles[index];
      console.log(this.shapes[v]);
      console.log(this.circles[index].x);
      console.log(this.circles[index].y);
      }
      if (type === 'rect') {
        
      for(let i=0;i<this.shapes.length;i++){
        if(this.shapes[i]===this.rectangles[index])
        {
             v=i;
             break;
        }
      }
      this.rectangles[index].x=e.target.x();
      this.rectangles[index].y=e.target.y();
      this.shapes[v]=this.rectangles[index];
      console.log(this.rectangles[index].x);
      console.log(this.rectangles[index].y);
      }
      if (type === 'ellipse') {
      
      for(let i=0;i<this.shapes.length;i++){
        if(this.shapes[i]===this.ellipses[index])
        {
             v=i;
             break;
        }
      }
      this.ellipses[index].x=e.target.x();
      this.ellipses[index].y=e.target.y();
      this.shapes[v]=this.ellipses[index];
      console.log(this.ellipses[index].x);
      console.log(this.ellipses[index].y);
      }
      if (type === 'line') {
    
      for(let i=0;i<this.shapes.length;i++){
        if(this.shapes[i]===this.lines[index])
        {
             v=i;
             break;
        }
      }
      this.lines[index].x=e.target.x();
      this.lines[index].y=e.target.y();
      this.shapes[v]=this.lines[index];
      console.log(this.lines[index].x);
      console.log(this.lines[index].y);
      }
      if (type === 'square') {
      
      for(let i=0;i<this.shapes.length;i++){
        if(this.shapes[i]===this.squares[index])
        {
             v=i;
             break;
        }
      }
      this.squares[index].x=e.target.x();
      this.squares[index].y=e.target.y();
      this.shapes[v]=this.squares[index];
      console.log(this.squares[index].x);
      console.log(this.squares[index].y);
      }
      if (type === 'triangle') {
      
      for(let i=0;i<this.shapes.length;i++){
        if(this.shapes[i]===this.triangles[index])
        {
             v=i;
             break;
        }
      }
      this.triangles[index].x=e.target.x();
      this.triangles[index].y=e.target.y();
      this.shapes[v]=this.triangles[index];
      console.log(this.triangles[index].x);
      console.log(this.triangles[index].y);
      }
      
    },
      rect(){
           this.rectangle=true;
         this.circ=false;
        this.ellips=false;
        this.lin=false;
        this.sqrt=false;
        this.tria=false;
        this.cofill=false;
        this.delete=false;
        this.coledge=false;
        this.bru=false;
      },
      brush(){
             this.rectangle=false;
         this.circ=false;
        this.ellips=false;
        this.lin=false;
        this.sqrt=false;
        this.tria=false;
        this.cofill=false;
        this.delete=false;
        this.coledge=false;
        this.bru=true;
      },
      ellipse()
      {
         this.rectangle=false;
         this.circ=false;
        this.ellips=true;
        this.lin=false;
        this.sqrt=false;
        this.tria=false;
        this.cofill=false;
        this.delete=false;
        this.coledge=false;
        this.bru=false;
      }, 
      circle()
      {
           this.rectangle=false;
         this.circ=true;
        this.ellips=false;
        this.lin=false;
        this.sqrt=false;
        this.tria=false;
        this.cofill=false;
        this.delete=false;
        this.coledge=false;
        this.bru=false;
      },
      line()
      {
             this.rectangle=false;
         this.circ=false;
        this.ellips=false;
        this.lin=true;
        this.sqrt=false;
        this.tria=false;
        this.cofill=false;
        this.delete=false;
        this.coledge=false;
        this.bru=false;
      },
      square()
      {
  
            this.rectangle=false;
         this.circ=false;
        this.ellips=false;
        this.lin=false;
        this.sqrt=true;
        this.tria=false;
        this.cofill=false;
        this.delete=false;
        this.coledge=false;
        this.bru=false;
      },
      triangle()
      {
            this.rectangle=false;
         this.circ=false;
        this.ellips=false;
        this.lin=false;
        this.sqrt=false;
        this.tria=true;
        this.cofill=false;
        this.delete=false;
        this.coledge=false;
        this.bru=false;
      },
      fill(){
  
            this.rectangle=false;
         this.circ=false;
        this.ellips=false;
        this.lin=false;
        this.sqrt=false;
        this.tria=false;
        this.cofill=true;
        this.delete=false;
        this.coledge=false;
        this.bru=false;
  
      },
      edge(){
          this.rectangle=false;
         this.circ=false;
        this.ellips=false;
        this.lin=false;
        this.sqrt=false;
        this.tria=false;
        this.cofill=false;
        this.delete=false;
        this.coledge=true;
        this.bru=false;
  
      },
      clr()
      {
        this.circles=[];
        this.lines=[];
        this.squares=[];
        this.rectangles=[];
        this.triangles=[];
        this.ellipses=[];
        this.shapes=[];
      
      },
      resize(){
        this.resiz=true;
        this.userInput = document.getElementById('userInput').value;
  
      },
       shapeClicked(type, index) {
        if(type==='triangle')
       {
  
  
  
        this.triangles[index].draggable=true;
          let v=0;
          for(let i=0;i<this.shapes.length;i++)
          {
            if(this.shapes[i]===this.triangles[index])
            {
              v=i;
              break;
            }
          }
        if(this.resiz)
        {
         
          this.triangles[index].draggable=false;
          this.triangles[index].radius *=  this.userInput;
          this.triangles[index].draggable=true;
          this.shapes[v]=this.triangles[index];
          this.resiz=false;
  
        }
          if(this.delete)
          {
            
                this.triangles.splice(index,1);
                this.shapes.splice(v,1);
               // console.log(this.shapes);
                this.delete=false;
          }
  
         if (this.cofill) {
          let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
          this.selectedColor2= selectedFillColor
          this.triangles[index].fill = selectedFillColor;
          this.shapes[v]=this.triangles[index];       // Assign the selected fill color to the fill property of the shape object
          selectedFillColor=this.pureColor
          this.cofill = false;
          this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
          
                   }
               if(this.coledge){
                let selectedEdgeColor = this.pureColor; // Use the selected edge color
            this.triangles[index].stroke = selectedEdgeColor; 
            this.shapes[v]=this.triangles[index]; // Assign the selected edge color to the stroke property of the shape object
            this.coledge = false;
            this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                  
         
            }
         
         /*********************************************************************** */
          
       }
       else if(type==='circle')
       {
        this.circles[index].draggable=true;
         let v=0;
          for(let i=0;i<this.shapes.length;i++)
          {
            if(this.shapes[i]===this.circles[index])
            {
              v=i;
              break;
            }
          }
        if(this.resiz)
        {
          this.circles[index].draggable=false;
          this.circles[index].radius *= this.userInput;
          this.circles[index].draggable=true;
          this.shapes[v]=this.circles[index];
          this.resiz=false;
        }
            if(this.delete)
          {
           
                this.circles.splice(index,1);
                this.shapes.splice(v,1);
  
                  this.delete=false;
          }
          if (this.cofill) {
  
          let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
          this.circles[index].fill = selectedFillColor; 
             this.shapes[v]=this.circles[index];   // Assign the selected fill color to the fill property of the shape object
          selectedFillColor=this.pureColor
          this.cofill = false;
          this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
             
                
                   }
                  if(this.coledge){
               let selectedEdgeColor = this.pureColor; // Use the selected edge color
            this.circles[index].stroke = selectedEdgeColor; 
            this.shapes[v]=this.circles[index]; // Assign the selected edge color to the stroke property of the shape object
            this.coledge = false;
            this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                  
          
         
       }  
         
       }
       else if(type==='line')
       {
  
         let v=0;
          for(let i=0;i<this.shapes.length;i++)
          {
            if(this.shapes[i]===this.lines[index])
            {
              v=i;
              break;
            }
          }
               if(this.delete)
          {
           
                this.lines.splice(index,1);
                this.shapes.slice(v,1);
                  this.delete=false;
          }
          if(this.coledge){
               let selectedEdgeColor = this.pureColor; // Use the selected edge color
            this.lines[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
            this.coledge = false;
            this.shapes[v]=this.lines[index];
            this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                  
          }
       }
       /************************************************************ */
       else if(type==='rect')
       {
        this.rectangles[index].draggable=true;
         let v=0;
          for(let i=0;i<this.shapes.length;i++)
          {
            if(this.shapes[i]===this.rectangles[index])
            {
              v=i;
              break;
            }
          }
  
        this.rectangles[index].draggable=true;
        if(this.resiz)
        {
          this.rectangles[index].draggable=false;
          this.rectangles[index].width *= this.userInput;
          this.rectangles[index].height *= this.userInput;
          this.rectangles[index].draggable=true;
          this.shapes[v]=this.rectangles[index];
          this.resiz=false;
        }
               if(this.delete)
          {
           
                this.rectangles.splice(index,1);
                this.shapes.splice(v,1);
                  this.delete=false;
          }
          if (this.cofill) {
          let selectedFillColor = this.pureColor; // Store the selected fill color in a variable
          this.rectangles[index].fill = selectedFillColor; // Assign the selected fill color to the fill property of the shape object
          //selectedFillColor=this.pureColor
          this.shapes[v]=this.rectangles[index];
          this.cofill = false;
          this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
          
                   }
          if(this.coledge){
               let selectedEdgeColor = this.pureColor; // Use the selected edge color
            this.rectangles[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
            this.shapes[v]=this.rectangles[index];
            this.coledge = false;
            this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                  
          
         
       }
       }
       /*************************************************************** */
        else if(type==='ellipse') {
          this.ellipses[index].draggable=true;
         let v=0;
          for(let i=0;i<this.shapes.length;i++)
          {
            if(this.shapes[i]===this.ellipses[index])
            {
              v=i;
              break;
            }
          }
        if(this.resiz)
        {
          this.ellipses[index].draggable=false;
          this.ellipses[index].radiusX *=  this.userInput;
          this.ellipses[index].radiusY *=  this.userInput;
          this.ellipses[index].draggable=true;
          this.shapes[v]=this.ellipses[index];
          this.resiz=false;
        }
                  if(this.delete)
          {
           
                this.ellipses.splice(index,1);
                this.shapes.splice(v,1);
                  this.delete=false;
          }
            if (this.cofill) {
        let selectedFillColor = this.pureColor;
        this.ellipses[index].fill = selectedFillColor;
        this.shapes[v]=this.ellipses[index];
        this.cofill = false;
        this.pureColor = '#ffffff';
      }
  
          if(this.coledge){
             let selectedEdgeColor = this.pureColor; // Use the selected edge color
            this.ellipses[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
              this.shapes[v]=this.ellipses[index];
            this.coledge = false;
            this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                  
         
       }
          }
          /**************************************************** */
       else if(type==='square')
       {
        this.squares[index].draggable=true;
          let v=0;
          for(let i=0;i<this.shapes.length;i++)
          {
            if(this.shapes[i]===this.squares[index])
            {
              v=i;
              break;
            }
          }
        if(this.resiz)
        {
          this.squares[index].draggable=false;
          this.squares[index].width *=  this.userInput;
          this.squares[index].height *=  this.userInput;
         
          this.squares[index].draggable=true;
           this.shapes[v]=this.squares[index];
        }
       // this.squares[index].fill = this.selectedColor;
                 if(this.delete)
          {
           
                this.squares.splice(index,1);
                this.shapes.splice(v,1);
                  this.delete=false;
          }
        if (this.cofill) {
          let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
          this.selectedColor2= selectedFillColor
          this.squares[index].fill = selectedFillColor; // Assign the selected fill color to the fill property of the shape object
          selectedFillColor=this.pureColor
            this.shapes[v]=this.squares[index];
          this.cofill = false;
          this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
          
                   }
          if(this.coledge)
          {
                    let selectedEdgeColor = this.pureColor; // Use the selected edge color
            this.squares[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
            this.coledge = false;
              this.shapes[v]=this.squares[index];
            this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                  
          }         
         
         
       }
  
      },
   
  
      del()
      {
                this.rectangle=false;
         this.circ=false;
        this.ellips=false;
        this.lin=false;
        this.sqrt=false;
        this.tria=false;
        this.cofill=false;
        this.delete=true;
        this.coledge=false;
        this.bru=false;
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
            console.log(this.currentShape.x);
            console.log(this.currentShape.y);
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
              width: 0,
              height: 0,
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
        /*
        else if(this.bru)
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
        */
    
       
        
      
      },
      stopDrawing() {
       
            if (this.isdraw) {
          this.isdraw = false;
          console.log("xxxxx");
          if(this.circ)
          {
                       this.circles.push({ ...this.currentShape });
                       this.shapes.push({...this.currentShape});
                     // while(true){
                     // console.log(this.shapes[0]);
                     // }
          }
        
        else if(this.rectangle){
            this.rectangles.push({...this.currentShape});
              this.shapes.push({...this.currentShape});
        }
        
        else if(this.ellips)
        {
             this.ellipses.push({...this.currentShape});
               this.shapes.push({...this.currentShape});
        }
             
        else if(this.lin||this.bru)
        {
               this.lines.push({...this.currentShape});
                 this.shapes.push({...this.currentShape});
        }
            
        else if(this.sqrt)
        {
                  this.squares.push({...this.currentShape});
                    this.shapes.push({...this.currentShape});
        }
            
        else if(this.tria)
        {
               this.triangles.push({...this.currentShape}); 
                  this.shapes.push({...this.currentShape});
        }
                
  
  
  
          this.currentShape = null;
          this.circ=false;
          this.ellips=false;
          this.rectangle=false;
          this.lin=false;
          this.sqrt=false;
          this.tria=false;
          this.mov = false;
          this.bru=false;
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
              this.currentShape.width = position.x - this.currentShape.x;
              this.currentShape.height = position.x - this.currentShape.x;
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
  
      async saving()
      {
        await fetch('http://localhost:8080/save', {
          method: 'POST',
          body: this.path,
        }).catch(error => {
          console.error('Fetch error:', error);
        });
      }
    }
  }
  
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