<template>
  <div class="appaint" style="border:solid black 2px; width:1130px; background-color:#404340 ;">
    <div class="header">
  <div class="operations" >
    <button style="background-color:rgb(111, 125, 131)" @click="laod()">open <i class="fa-solid fa-folder-open"></i></button>
    <button style="background-color:rgb(111, 125, 131)" @click="save()">Save <i class="fa-solid fa-floppy-disk"></i></button>
    <button style="background-color:rgb(111, 125, 131)" @click="undo()">Undo <i class="fa-solid fa-arrow-rotate-left"></i></button>
    <button style="background-color:rgb(111, 125, 131)" @click="Redo()">Redo <i class="fa-solid fa-rotate-right"></i></button>
    <button  style="background-color:rgb(111, 125, 131)" @click="del()">Delete <i class="fa-solid fa-eraser"></i></button>
    <button style="background-color:rgb(111, 125, 131)"  @click="clr()">clear <i class="fa-solid fa-trash"></i></button>
    <button style="background-color:rgb(111, 125, 131)" @click="copy()">copy <i class="fa-solid fa-copy"></i></button>
    <button for="userInput"  style="background-color:rgb(111, 125, 131)" @click="resize() ">resize</button>
    <input type="text" id="userInput" style="border:solid black ;">
    

     <v-dialog width="400" class="saving" v-model="savdialog" transition="dialog-top-transition">
        <v-card variant="outlined">
          <v-card-title><h1>save</h1></v-card-title>
          <v-card-subtitle> xml OR jason  </v-card-subtitle>
          <v-btn :style="{ backgroundColor: savjason === true ? '#2196F3' : 'initial', color: savjason === true ? '#FFFFFF' : 'initial' }" @click="savejason()">jason</v-btn>
          <div style="height:5px;"></div>
          <v-btn :style="{ backgroundColor: savxml === true ? '#2196F3' : 'initial', color: savxml === true ? '#FFFFFF' : 'initial' }" @click="savexml()">xml</v-btn>
          <v-card-actions >
            <v-btn  variant="outlined" @click="submity() ">OK</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog width="400" class="saving" v-model="pathdialog" transition="dialog-bottom-transition">
        <v-card variant="outlined">
          <v-card-title>path of saving</v-card-title>
          <input type="text" placeholder="c:\\name" id="path" autofocus>
          <v-card-actions >
            <v-btn  variant="outlined" @click="send()">save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog width="400" class="saving" v-model="loaddialog" transition="dialog-top-transition">
        <v-card variant="outlined">
          <v-card-title><h1>Open</h1></v-card-title>
          <v-card-subtitle> xml OR jason  </v-card-subtitle>
          <v-btn :style="{ backgroundColor: lodjason === true ? '#2196F3' : 'initial', color: lodjason === true ? '#FFFFFF' : 'initial' }" @click="loadjason()">jason</v-btn>
          <div style="height:5px;"></div>
          <v-btn :style="{ backgroundColor: lodxml === true ? '#2196F3' : 'initial', color: lodxml === true ? '#FFFFFF' : 'initial' }" @click="loadxml()">xml</v-btn>
          <v-card-actions >
            <v-btn  variant="outlined" @click="ok() ">OK</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
      <v-dialog width="400" class="saving" v-model="pathloaddialog" transition="dialog-bottom-transition">
        <v-card variant="outlined">
          <v-card-title>path of file</v-card-title>
          <input type="text" placeholder="c:\\name" id="pathload" autofocus>
          <v-card-actions >
            <v-btn  variant="outlined" @click="open()">open</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
   



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
          type:'Rectangle',
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
         @mouseenter="oldpo('rect', index)"
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
          @mouseenter="oldpo('circle', index)"
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
        @mouseenter="oldpo('ellipse', index)"
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
          strokeWidth: 4,
          draggable:true,
        }"
         @click="shapeClicked('line', index)"
          @mouseenter="oldpo('line', index)"
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
       @mouseenter="oldpo('square', index)"
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
      @mouseenter="oldpo('triangle', index)"
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
      path:"" ,
      shapeType: '',
      ord:null,
      modifysh:null,
      cop:false,
      co:null,
      cc:null,
      vv:null,
      tridialog:false,
        sqrdialog:false,
        circdialog:false,
        rectcdialog:false,
        elpsdialog:false,
        savdialog:false,
        savjason:false,
        savxml:false,
        pathdialog:false,
        loaddialog:false,
        pathloaddialog:false,
        lodjason:false,
        lodxml:false,


    };
  },
  methods: {
    setup() {
      const pureColor = ref<ColorInputWithoutInstance>("red");
      return{pureColor}
    },
    // save()
    // {
    //     this.saving();
    // },

    oldpo(type, index)
    {
        if(type==='triangle')
        {
            this.cc=this.triangles[index].x;
            this.vv=this.triangles[index].y;
        }
        else if(type==='rect')
        {
          this.cc=this.rectangles[index].x;
            this.vv=this.rectangles[index].y;
        }
        else if(type==='line')
        {
          this.cc=this.lines[index].x;
            this.vv=this.lines[index].y;
        }
        else if(type==='ellipse')
        {
             this.cc=this.ellipses[index].x;
            this.vv=this.ellipses[index].y;
        }
        else if(type==='square')
        {
          
             this.cc=this.squares[index].x;
            this.vv=this.squares[index].y;
        }
        else if(type==='circle')
        {
           this.cc=this.circles[index].x;
            this.vv=this.circles[index].y;
        }
    },

newpo(type, index,e) {
   var v=0;
  if (type === 'circle') {
    this.shapeType = "Circle"
     for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
                if(this.shapes[i].radius===this.circles[index].radius)
                {
                   

                   if(this.shapes[i].fill===this.circles[index].fill)
                   {
                      if(this.shapes[i].stroke===this.circles[index].stroke)
                      {
                             v=i;
                            console.log(v);
                            break;
                      }
                   }
                }


                       

            }

          
          }
          }
         
        }
    this.circles[index].x=e.target.x();
    this.circles[index].y=e.target.y();
    this.shapes[v].x=this.circles[index].x;
    this.shapes[v].y=this.circles[index].y;
    this.modifysh=this.shapes[v];
    this.ord=v;
    this.modify()
    console.log(this.shapes[v]);
    console.log(this.circles[index].x);
    console.log(this.circles[index].y);
    }
    if (type === 'rect') {
      this.shapeType = "Rectangle"
     for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                 if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
                if(this.shapes[i].width===this.rectangles[index].width)
                {
                   

                   if(this.shapes[i].fill===this.rectangles[index].fill)
                   {
                      if(this.shapes[i].stroke===this.rectangles[index].stroke)
                      {
                        if(this.shapes[i].height===this.rectangles[index].height)
                        {
                                 
                             v=i;
                            console.log(v);
                            break;
                        }
                      }
                   }
                }


                       

            }

          
          }
          }
        
        }
    this.rectangles[index].x=e.target.x();
    this.rectangles[index].y=e.target.y();
    this.shapes[v].x=this.rectangles[index].x;
    this.shapes[v].y=this.rectangles[index].y;
      this.modifysh=this.shapes[v];
    this.ord=v;
    this.modify()
    console.log(this.rectangles[index].x);
    console.log(this.rectangles[index].y);
    }
    if (type === 'ellipse') {
      this.shapeType = "Ellipse"
    for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                  if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
                if(this.shapes[i].radiusX===this.ellipses[index].radiusX)
                {
                   

                   if(this.shapes[i].fill===this.ellipses[index].fill)
                   {
                      if(this.shapes[i].stroke===this.ellipses[index].stroke)
                      {
                        if(this.shapes[i].radiusY===this.ellipses[index].radiusY)
                             v=i;
                            console.log(v);
                            break;
                      }
                   }
                }


                       

            }

          
          }
          }
       
        }
    this.ellipses[index].x=e.target.x();
    this.ellipses[index].y=e.target.y();
    this.shapes[v].x=this.ellipses[index].x;
    this.shapes[v].y=this.ellipses[index].y;
      this.modifysh=this.shapes[v];
    this.ord=v;
    this.modify()
    console.log(this.ellipses[index].x);
    console.log(this.ellipses[index].y);
    }
    if (type === 'line') {
      this.shapeType = "Line"
    for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
       if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
          
                   
                        
                                 
                             v=i;
                            console.log(v);
                            break;
            }

          
          }
          }
          
        }
    this.lines[index].x=e.target.x();
    this.lines[index].y=e.target.y();
    this.shapes[v].x=this.lines[index].x;
    this.shapes[v].y=this.lines[index].y;
      this.modifysh=this.shapes[v];
    this.ord=v;
    this.modify()
    console.log(this.lines[index].x);
    console.log(this.lines[index].y);
    }
    if (type === 'square') {
      this.shapeType = "Square"
    for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                  if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
                if(this.shapes[i].width===this.squares[index].width)
                {
                   

                   if(this.shapes[i].fill===this.squares[index].fill)
                   {
                      if(this.shapes[i].stroke===this.squares[index].stroke)
                      {
                        if(this.shapes[i].height===this.squares[index].height)
                        {
                                 
                             v=i;
                            console.log(v);
                            break;
                        }
                      }
                   }
                }


                       

            }

          
          }
          }
        
        }
    this.squares[index].x=e.target.x();
    this.squares[index].y=e.target.y();
    this.shapes[v].x=this.squares[index].x;
    this.shapes[v].y=this.squares[index].y;
      this.modifysh=this.shapes[v];
    this.ord=v;
    this.modify()
    console.log(this.squares[index].x);
    console.log(this.squares[index].y);
    }
    if (type === 'triangle') {
      this.shapeType = "Triangle"
    
        for(let i=0;i<this.shapes.length;i++)
        {
              if(this.shapes[i]!==null)
              {
                        if(this.shapes[i].x===this.cc)
              {
                if(this.shapes[i].y===this.vv)
                { 
                    if(this.shapes[i].radius===this.triangles[index].radius)
                    {
                      

                      if(this.shapes[i].fill===this.triangles[index].fill)
                      {
                          if(this.shapes[i].stroke===this.triangles[index].stroke)
                          {
                                v=i;
                                console.log(v);
                                break;
                          }
                      }
                    }


                          

                }

              
              }
              }
         
        }
         console.log(v);
    this.triangles[index].x=e.target.x();
    this.triangles[index].y=e.target.y();
    this.shapes[v].x=this.triangles[index].x;
    this.shapes[v].y=this.triangles[index].y;
    this.modifysh=this.shapes[v];
    this.ord=v;
    this.modify()
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
    copy()
    {
      this.cop=true;
    },

    async shapeClicked(type, index) {
      if(type==='triangle')
     {
      this.shapeType = "Triangle"
         console.log(index);
         //console.log(this.shapes.length);
           this.triangles[index].draggable=true;
        let v=0;
      for(let i=0;i<this.shapes.length;i++)
        {
              if(this.shapes[i]!==null)
              {
                        if(this.shapes[i].x===this.cc)
              {
                if(this.shapes[i].y===this.vv)
                { 
                    if(this.shapes[i].radius===this.triangles[index].radius)
                    {
                      

                      if(this.shapes[i].fill===this.triangles[index].fill)
                      {
                          if(this.shapes[i].stroke===this.triangles[index].stroke)
                          {
                                v=i;
                                console.log(v);
                                break;
                          }
                      }
                    }


                          

                }

              
              }
              }
         
        }
        // console.log(v);
        console.log(this.shapes)
        console.log(this.triangles)

        if(this.cop)
        {
          this.ord=v;
          console.log(this.shapes[v])
          await this.prototype();
          console.log(this.co)
          this.triangles.push({...this.co})
          this.shapes.push({...this.co})
          this.cop = false
        }
      if(this.resiz)
      {
       
        this.triangles[index].draggable=false;
        this.triangles[index].radius *=  this.userInput;
        this.triangles[index].draggable=true;
        this.shapes[v].radius=this.triangles[index].radius;
        this.resiz=false;
          this.modifysh=this.shapes[v];
              this.ord=v;
              this.modify()

      }
        if(this.delete)
        {
          
              this.triangles.splice(index,1);
             // console.log(this.shapes);
               this.modifysh=this.shapes[v];
                    this.ord=v;
                    this.modify2()
              this.delete=false;
        }

       if (this.cofill) {
        let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
        this.selectedColor2= selectedFillColor
        this.triangles[index].fill = selectedFillColor;
        this.shapes[v].fill=this.triangles[index].fill;       // Assign the selected fill color to the fill property of the shape object
        selectedFillColor=this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
          this.modifysh=this.shapes[v];
             this.ord=v;
              this.modify()
                 }
             if(this.coledge){
              let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.triangles[index].stroke = selectedEdgeColor; 
          this.shapes[v].stroke=this.triangles[index].stroke; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                 this.modifysh=this.shapes[v];
                  this.ord=v;
                 this.modify() 
       
          }
       
       /*********************************************************************** */
        
     }
     else if(type==='circle')
     {
       this.shapeType = "Circle"
       let v=0;
      for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
                if(this.shapes[i].radius===this.circles[index].radius)
                {
                   

                   if(this.shapes[i].fill===this.circles[index].fill)
                   {
                      if(this.shapes[i].stroke===this.circles[index].stroke)
                      {
                             v=i;
                            console.log(v);
                            break;
                      }
                   }
                }


                       

            }

          
          }
          }
         
        }
         if(this.cop)
        {
          this.ord=v;
          console.log(this.shapes[v])
          await this.prototype();
          console.log(this.co)
          this.circles.push({...this.co})
          this.shapes.push({...this.co})
          this.cop = false
           
        }
      if(this.resiz)
      {
        this.circles[index].draggable=false;
        this.circles[index].radius *= this.userInput;
        this.circles[index].draggable=true;
        this.shapes[v].radius=this.circles[index].radius;
        this.resiz=false;
          this.modifysh=this.shapes[v];
           this.ord=v;
           this.modify()
      }
          if(this.delete)
        {
         
              this.circles.splice(index,1);

                this.delete=false;
                    this.ord=v;
                  this.modify2()
        }
        if (this.cofill) {

        let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
        this.circles[index].fill = selectedFillColor; 
           this.shapes[v].fill=this.circles[index].fill;   // Assign the selected fill color to the fill property of the shape object
        selectedFillColor=this.pureColor
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
             this.modifysh=this.shapes[v];
              this.ord=v;
            this.modify()
              
                 }
                if(this.coledge){
             let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.circles[index].stroke = selectedEdgeColor; 
          this.shapes[v].stroke=this.circles[index].stroke; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
              this.modifysh=this.shapes[v];
              this.ord=v;
              this.modify()    
        
       
     }  
       
     }
     else if(type==='line')
     {
      let v=0;
       this.shapeType = "Line"
    for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                 if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
          


                       
                               
                             v=i;
                            console.log(v);
                            break;
                              


            }

          
          }
          }
          
        }
         if(this.cop)
        {
          this.ord=v;
          console.log(this.shapes[v])
          await this.prototype();
          console.log(this.co)
          this.lines.push({...this.co})
          this.shapes.push({...this.co})
          this.cop = false
           
           
        }
             if(this.delete)
        {
         
              this.lines.splice(index,1);
                this.delete=false;
                  this.ord=v;
                  this.modify2()
        }
        if(this.coledge){
             let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.lines[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
          this.shapes[v].stroke=this.lines[index].stroke;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                  this.modifysh=this.shapes[v];
                    this.ord=v;
                  this.modify()
        }
     }
     /************************************************************ */
     else if(type==='rect')
     {
      let v=0;
       this.shapeType = "Rectangle"
     for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                 if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
                if(this.shapes[i].width===this.rectangles[index].width)
                {
                   

                   if(this.shapes[i].fill===this.rectangles[index].fill)
                   {
                      if(this.shapes[i].stroke===this.rectangles[index].stroke)
                      {
                        if(this.shapes[i].height===this.rectangles[index].height)
                        {
                                 
                             v=i;
                            console.log(v);
                            break;
                        }
                      }
                   }
                }


                       

            }

          
          }
          }
        
        }
         if(this.cop)
        {
          this.ord=v;
          console.log(this.shapes[v])
          await this.prototype();
          console.log(this.co)
          this.rectangles.push({...this.co})
          this.shapes.push({...this.co})
          this.cop = false
           
        }
      this.rectangles[index].draggable=true;
      if(this.resiz)
      {
        this.rectangles[index].draggable=false;
        this.rectangles[index].width *= this.userInput;
        this.rectangles[index].height *= this.userInput;
        this.rectangles[index].draggable=true;
        this.shapes[v].width=this.rectangles[index].width;
        this.shapes[v].height=this.rectangles[index].height;
        this.resiz=false;
          this.modifysh=this.shapes[v];
            this.ord=v;
          this.modify()
      }
             if(this.delete)
        {
         
              this.rectangles.splice(index,1);
                this.delete=false;
                this.ord=v;
              this.modify2()
        }
        if (this.cofill) {
        let selectedFillColor = this.pureColor; // Store the selected fill color in a variable
        this.rectangles[index].fill = selectedFillColor; // Assign the selected fill color to the fill property of the shape object
        //selectedFillColor=this.pureColor
        this.shapes[v].fill=this.rectangles[index].fill;
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
                this.modifysh=this.shapes[v];
                this.ord=v;
                 this.modify()
                 }
        if(this.coledge){
             let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.rectangles[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.shapes[v].stroke=this.rectangles[index].stroke;
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
               this.modifysh=this.shapes[v];
                this.ord=v;
               this.modify()   
        
       
     }
     }
     /*************************************************************** */
      else if(type==='ellipse') {
        let v=0;
         this.shapeType = "Ellipse"
   for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                  if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
                if(this.shapes[i].radiusX===this.ellipses[index].radiusX)
                {
                   

                   if(this.shapes[i].fill===this.ellipses[index].fill)
                   {
                      if(this.shapes[i].stroke===this.ellipses[index].stroke)
                      {
                        if(this.shapes[i].radiusY===this.ellipses[index].radiusY)
                             v=i;
                            console.log(v);
                            break;
                      }
                   }
                }


                       

            }

          
          }
          }
       
        }
        if(this.cop)
        {
          this.ord=v;
          console.log(this.shapes[v])
          await this.prototype();
          console.log(this.co)
          this.ellipses.push({...this.co})
          this.shapes.push({...this.co})
          this.cop = false
           
        }
      if(this.resiz)
      {
        this.ellipses[index].draggable=false;
        this.ellipses[index].radiusX *=  this.userInput;
        this.ellipses[index].radiusY *=  this.userInput;
        this.ellipses[index].draggable=true;
        this.shapes[v].radiusX=this.ellipses[index].radiusx;
                this.shapes[v].radiusY=this.ellipses[index].radiusY;

        this.resiz=false;
          this.modifysh=this.shapes[v];
          this.ord=v;
          this.modify();

      }
                if(this.delete)
        {
         
              this.ellipses.splice(index,1);
                this.delete=false;
               this.modifysh=this.shapes[v];
                   this.ord=v;
                  this.modify2()

        }
          if (this.cofill) {
      let selectedFillColor = this.pureColor;
      this.ellipses[index].fill = selectedFillColor;
      this.shapes[v].fill=this.ellipses[index].fill;
      this.cofill = false;
      this.pureColor = '#ffffff';
        this.modifysh=this.shapes[v];
           this.ord=v;
         this.modify()
    }

        if(this.coledge){
           let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.ellipses[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
            this.shapes[v].stroke=this.ellipses[index].stroke;
          this.coledge = false;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
                 this.modifysh=this.shapes[v];
               this.ord=v;
            this.modify() 
       
     }
        }
        /**************************************************** */
     else if(type==='square')
     {
      let v=0;
        this.shapeType = "Square"
    for(let i=0;i<this.shapes.length;i++)
        {
          if(this.shapes[i]!==null)
          {
                  if(this.shapes[i].x===this.cc)
          {
            if(this.shapes[i].y===this.vv)
            { 
                if(this.shapes[i].width===this.squares[index].width)
                {
                   

                   if(this.shapes[i].fill===this.squares[index].fill)
                   {
                      if(this.shapes[i].stroke===this.squares[index].stroke)
                      {
                        if(this.shapes[i].height===this.squares[index].height)
                        {
                                 
                             v=i;
                            console.log(v);
                            break;
                        }
                      }
                   }
                }


                       

            }

          
          }
          }
        
        }
         if(this.cop)
        {
         this.ord=v;
          console.log(this.shapes[v])
          await this.prototype();
          console.log(this.co)
          this.squares.push({...this.co})
          this.shapes.push({...this.co})
          this.cop = false
           
           
        }
      if(this.resiz)
      {
        this.squares[index].draggable=false;
        this.squares[index].width *=  this.userInput;
        this.squares[index].height *=  this.userInput;
       
        this.squares[index].draggable=true;
         this.shapes[v].width=this.squares[index].width;
         this.shapes[v].height=this.squares[index].height;
           this.modifysh=this.shapes[v];
            this.ord=v;
            this.modify()
            this.resiz=false;
      }
     // this.squares[index].fill = this.selectedColor;
               if(this.delete)
        {
         
              this.squares.splice(index,1);
                this.delete=false;
                  this.ord=v;
                this.modify2()
        }
      if (this.cofill) {
        let   selectedFillColor = this.pureColor; // Store the selected fill color in a variable
        this.selectedColor2= selectedFillColor
        this.squares[index].fill = selectedFillColor; // Assign the selected fill color to the fill property of the shape object
        selectedFillColor=this.pureColor
          this.shapes[v].fill=this.squares[index].fill;
        this.cofill = false;
        this.pureColor = '#ffffff'; // Reset the pureColor to a default value or another selected fill color
          this.modifysh=this.shapes[v];
          this.ord=v;
           this.modify()
        
                 }
        if(this.coledge)
        {
                  let selectedEdgeColor = this.pureColor; // Use the selected edge color
          this.squares[index].stroke = selectedEdgeColor; // Assign the selected edge color to the stroke property of the shape object
          this.coledge = false;
            this.shapes[v].stroke=this.squares[index].stroke;
          this.pureColor = '#ffffff'; // Reset the pureColor2 to a default value or another selected edge color
            this.modifysh=this.shapes[v];
            this.ord=v;
           this.modify()
                
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
            strokeWidth:2,
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
            strokeWidth:2,
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
            strokeWidth:2,
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
                x: position.x,
                y: position.y,
            stroke:this.pureColor2,
            strokeWidth:4,
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

            strokeWidth:2,
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
                strokeWidth:2,
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
          this.shapeType = 'Circle';
                     this.circles.push({ ...this.currentShape });
                     this.shapes.push({...this.currentShape});
                 
        }
      
      else if(this.rectangle){
        this.shapeType = 'Rectangle';

          this.rectangles.push({...this.currentShape});
            this.shapes.push({...this.currentShape});
      }
      
      else if(this.ellips)
      {
        this.shapeType = 'Ellipse';

           this.ellipses.push({...this.currentShape});
             this.shapes.push({...this.currentShape});
      }
           
      else if(this.lin||this.bru)
      {
        this.shapeType = 'Line';

             this.lines.push({...this.currentShape});
               this.shapes.push({...this.currentShape});
      }
          
      else if(this.sqrt)
      {
        this.shapeType = 'Square';

                this.squares.push({...this.currentShape});
                  this.shapes.push({...this.currentShape});
      }
          
      else if(this.tria)
      {
        this.shapeType = 'Triangle';

             this.triangles.push({...this.currentShape}); 
                this.shapes.push({...this.currentShape});
      }
      this.createShape();



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
    async createShape(){
      await fetch('http://localhost:8080/create', {
        method: 'POST',
        body: (this.shapeType + JSON.stringify(this.currentShape)),
      }).catch(error => {
        console.error('Fetch error:', error);
      });
    },

    async modify()
    {
        await fetch('http://localhost:8080/modify', {
        method: 'POST',
        body: (this.ord + "{" + this.shapeType + JSON.stringify(this.modifysh)),
      }).catch(error => {
        console.error('Fetch error:', error);
      });
    },
    async prototype()
    {
      await fetch('http://localhost:8080/copy', {
        method: 'POST',
        body: (this.ord),
      })
      .then(res => res.json())
      .then(data => this.co = data)
    },
    async modify2()
    {
        await fetch('http://localhost:8080/delete', {
        method: 'POST',
        body: (this.ord + "," + this.shapeType),
      }).catch(error => {
        console.error('Fetch error:', error);
      });
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
        console.log(this.savejason)
        if(this.savjason){
        await fetch('http://localhost:8080/saveJson', {
          method: 'POST',
          body: this.path+".json",
        }).catch(error => {
          console.error('Fetch error:', error);
        });
      }
      if(this.savxml){
        await fetch('http://localhost:8080/saveXml', {
          method: 'POST',
          body: this.path + ".xml",
        }).catch(error => {
          console.error('Fetch error:', error);
        });
        console.log(this.savexml)
      }
      },

     async loading(){ 
        if(this.lodjason){
        await fetch('http://localhost:8080/loadJson', {
          method: 'POST',
          body: (this.path + ".json"),
        })
        .then(res => res.json())
        .then(data => this.shapes = data)
        console.log(this.shapes)
        
this.circles=[];
      this.lines=[];
      this.squares=[];
      this.rectangles=[];
      this.triangles=[];
      this.ellipses=[];
      
      for(let i=0;i<this.shapes.length;i++)
      {
        if(this.shapes[i]!==null)
        {
          if(this.shapes[i].type==='Rectangle')
          {
             this.rectangles.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Circle')
          {
              this.circles.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Ellipse')
          {
            this.ellipses.push({...this.shapes[i]})
          }
         else  if(this.shapes[i].type==='Square')
          {
             this.squares.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Triangle')
          {
            this.triangles.push({...this.shapes[i]});
          }
         else if(this.shapes[i].type==='Line')
         {
             this.lines.push({...this.shapes[i]});
         }
        }
         
      }
      

      }
      if(this.lodxml){
        await fetch('http://localhost:8080/loadXml', {
          method: 'POST',
          body: this.path+".xml",
        })
        .then(res => res.json())
        .then(data => this.shapes = data)
        console.log(this.shapes)
           
           this.circles=[];
      this.lines=[];
      this.squares=[];
      this.rectangles=[];
      this.triangles=[];
      this.ellipses=[];
      
      for(let i=0;i<this.shapes.length;i++)
      {
        if(this.shapes[i]!==null)
        {
               if(this.shapes[i].type==='Rectangle')
          {
             this.rectangles.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Circle')
          {
              this.circles.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Ellipse')
          {
            this.ellipses.push({...this.shapes[i]})
          }
         else  if(this.shapes[i].type==='Square')
          {
             this.squares.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Triangle')
          {
            this.triangles.push({...this.shapes[i]});
          }
         else if(this.shapes[i].type==='Line')
         {
             this.lines.push({...this.shapes[i]});
         }
        }
         
      }
    }

      },
        savejason(){
      this.savjason=true;
      this.savxml=false;
    },
    savexml(){
      this.savxml=true;
      this.savjason=false;
    }, 
      submity(){
        this.savdialog=false;
        this.pathdialog=true;
        

      } , 
      ok(){
        this.loaddialog=false;
        this.pathloaddialog=true;

      },
      save()
      {
          this.saving();
          this.savdialog=true;
      },  
      send(){
        var str=document.getElementById('path').value;
        for(let i=0 ;i<str.length;i++)
        {
          if(str[i]=='\\')
          {

            this.path+='\\'

          }
          this.path+=str[i];
        }
        console.log(this.savjason)
        console.log(this.savxml)
        console.log(this.path)
        this.pathdialog=false;
        this.saving();
        this.savjaso=false;
        this.savxml=false;
        this.path = ""
      },
      laod(){
        this.loaddialog=true;
      },
      loadjason(){
        this.lodjason=true;
        this.lodxml=false;

      },
      loadxml(){
        this.lodxml=true;
        this.lodjason=false;
      },
      open()
      {
        var str=document.getElementById('pathload').value;
        for(let i=0 ;i<str.length;i++)
        {
          if(str[i]=='\\')
          {

            this.path+='\\'

          }
          this.path+=str[i];
        }
        console.log(this.lodjason)
        console.log(this.lodxml)
        console.log(this.path)
        this.pathloaddialog=false;
        this.loading();
        this.lodjason=false;
        this.lodxml=false;
        this.path = ""

      },

     


    async undo()
    {
         await fetch('http://localhost:8080/undo', {
        method: 'GET',
      })
      .then(res => res.json())
      .then(data => this.shapes = data)
      
     console.log(this.shapes);
      this.circles=[];
      this.lines=[];
      this.squares=[];
      this.rectangles=[];
      this.triangles=[];
      this.ellipses=[];
      
      for(let i=0;i<this.shapes.length;i++)
      {
        if(this.shapes[i]!==null)
        {
               if(this.shapes[i].type==='Rectangle')
          {
             this.rectangles.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Circle')
          {
              this.circles.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Ellipse')
          {
            this.ellipses.push({...this.shapes[i]})
          }
         else  if(this.shapes[i].type==='Square')
          {
             this.squares.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Triangle')
          {
            this.triangles.push({...this.shapes[i]});
          }
         else if(this.shapes[i].type==='Line')
         {
             this.lines.push({...this.shapes[i]});
         }
        }
         
      }
    },
     async Redo()
    {
         await fetch('http://localhost:8080/redo', {
        method: 'GET',
      })
      .then(res => res.json())
      .then(data => this.shapes = data)
      
     console.log(this.shapes);
      this.circles=[];
      this.lines=[];
      this.squares=[];
      this.rectangles=[];
      this.triangles=[];
      this.ellipses=[];
      
      for(let i=0;i<this.shapes.length;i++)
      {
        if(this.shapes[i]!==null)
        {
               if(this.shapes[i].type==='Rectangle')
          {
             this.rectangles.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Circle')
          {
              this.circles.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Ellipse')
          {
            this.ellipses.push({...this.shapes[i]})
          }
         else  if(this.shapes[i].type==='Square')
          {
             this.squares.push({...this.shapes[i]});
          }
          else if(this.shapes[i].type==='Triangle')
          {
            this.triangles.push({...this.shapes[i]});
          }
         else if(this.shapes[i].type==='Line')
         {
             this.lines.push({...this.shapes[i]});
         }
        }
         
      }
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