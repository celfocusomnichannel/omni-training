(this["webpackJsonpomni-journey-ordermanagement"]=this["webpackJsonpomni-journey-ordermanagement"]||[]).push([[0],{0:function(e,t){e.exports=React},10:function(e,t){e.exports=ReactRedux},113:function(e,t){e.exports=UiLibCore.ListItemText},114:function(e,t){e.exports=UiLibCore.TextField},115:function(e){e.exports=JSON.parse('{"a":"omni-journey-ordermanagement","b":"ordermanagement"}')},167:function(e,t){e.exports=ReactDOM},17:function(e,t){e.exports=UiLibCore.Typography},170:function(e,t){e.exports=UiLibCore.Stepper},171:function(e,t){e.exports=UiLibCore.Step},172:function(e,t){e.exports=UiLibCore.StepLabel},173:function(e,t){e.exports=UiLibCore.Card},174:function(e,t){e.exports=UiLibCore.CardActionArea},175:function(e,t){e.exports=UiLibCore.CardActions},176:function(e,t){e.exports=UiLibCore.CardContent},177:function(e,t){e.exports=UiLibCore.CardMedia},178:function(e,t){e.exports=UiLibCore.Icon},179:function(e,t){e.exports=UiLibCore.DialogTitle},180:function(e,t){e.exports=UiLibCore.Dialog},181:function(e,t){e.exports=UiLibCore.List},182:function(e,t){e.exports=UiLibCore.ListItem},183:function(e,t){e.exports=UiLibCore.Select},184:function(e,t){e.exports=UiLibCore.MenuItem},189:function(e,t,n){e.exports=n(380)},31:function(e,t){e.exports=UiLibCore.styles},377:function(e,t,n){},380:function(e,t,n){"use strict";n.r(t);n(190),n(195),n(196),n(197),n(198),n(199),n(200),n(201),n(202),n(203),n(204),n(205),n(206),n(207),n(208),n(209),n(210),n(211),n(212),n(213),n(214),n(215),n(216),n(218),n(219),n(76),n(220),n(221),n(222),n(223),n(224),n(225),n(226),n(227),n(228),n(229),n(230),n(231),n(233),n(234),n(235),n(236),n(237),n(238),n(239),n(240),n(241),n(242),n(243),n(245),n(246),n(247),n(248),n(249),n(250),n(251),n(252),n(253),n(254),n(255),n(256),n(257),n(258),n(259),n(260),n(261),n(262),n(263),n(264),n(265),n(266),n(267),n(268),n(269),n(270),n(271),n(272),n(273),n(274),n(275),n(276),n(277),n(278),n(279),n(280),n(282),n(283),n(284),n(285),n(291),n(292),n(293),n(295),n(296),n(297),n(298),n(299),n(300),n(301),n(302),n(303),n(304),n(305),n(149),n(308),n(309),n(310),n(311),n(312),n(313),n(314),n(152),n(315),n(316),n(317),n(318),n(319),n(320),n(322),n(324),n(325),n(326),n(327),n(328),n(329),n(334),n(335),n(336),n(337),n(338),n(339),n(340),n(341),n(342),n(343),n(344),n(345),n(348),n(349),n(350),n(351),n(352),n(353),n(354),n(355),n(356),n(357),n(358),n(359),n(360),n(361),n(362),n(363),n(364),n(365),n(366),n(367),n(368),n(369),n(371),n(372),n(373),n(374),n(376),n(163),n(164);var r,a=n(0),c=n.n(a),o=n(167),s=n.n(o),i=n(168),u=n(169),l=n(187),d=n(186),m=n(26),p=n(10),f=n(53),v=n(15),E=n.n(v),b=n(34),h="/bin/mvc.do/ordermanagement/v1/app/",O=function(){return"/bin/mvc.do/ordermanagement/v1/app/products"},g=function(){return"/bin/mvc.do/ordermanagement/v1/app/category"},j=function(){return"/bin/mvc.do/ordermanagement/v1/app/delivery-options"},x=function(e,t){return"/bin/mvc.do/ordermanagement/v1/app/".concat(e,"/products/").concat(t)},y=function(e){return"/bin/mvc.do/ordermanagement/v1/app/".concat(e,"/order-create")},S=function(e){return"/bin/mvc.do/ordermanagement/v1/app/".concat(e,"/customer-info")},C=function(e){return"/bin/mvc.do/ordermanagement/v1/app/".concat(e,"/order-submit")},T=function(e){return"/bin/mvc.do/ordermanagement/v1/app/".concat(e)},_=function(){var e={url:h,method:"POST"};return r.request(e)},w=function(){var e={url:O(),method:"GET"};return r.request(e)},R=function(){var e={url:g(),method:"GET"};return r.request(e)},U=function(){var e={url:j(),method:"GET"};return r.request(e)},P=function(e){var t={url:T(e),method:"GET"};return r.request(t)},I=function(e,t){var n={url:x(e,t),method:"PUT"};return r.request(n)},N=function(e){var t={url:y(e),method:"POST"};return r.request(t)},D=function(e,t){var n={url:S(e),method:"POST",data:t};return r.request(n)},k=function(e){var t={url:C(e),method:"POST"};return r.request(t)},L=function(e){return{type:"READ_UPDATE_PROCESS",instance:e}},A=function(e){return{type:"SET_PREFERENCES",preferences:e}},B=function(){return function(e,t){var n;n=t().journey.services.HttpClient,r=n}},M=function(){return function(){var e=Object(b.a)(E.a.mark((function e(t){var n,r;return E.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,_();case 3:n=e.sent,r=n.data,t({type:"CREATE_INSTANCE",instance:r}),e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log(e.t0.message);case 11:case"end":return e.stop()}}),e,null,[[0,8]])})));return function(t){return e.apply(this,arguments)}}()},q=n(31),G=n(170),F=n.n(G),J=n(171),W=n.n(J),H=n(172),V=n.n(H),Y=n(54),z=n.n(Y),K=n(17),Q=n.n(K),X=n(188),Z=n(173),$=n.n(Z),ee=n(174),te=n.n(ee),ne=n(175),re=n.n(ne),ae=n(176),ce=n.n(ae),oe=n(177),se=n.n(oe),ie=n(178),ue=n.n(ie),le=n(179),de=n.n(le),me=n(180),pe=n.n(me),fe=n(181),ve=n.n(fe),Ee=n(182),be=n.n(Ee),he=n(113),Oe=n.n(he),ge=["onClose"];var je=Object(q.withStyles)({media:{height:140,width:250},card:{maxWidth:250,maxHeight:250,marginLeft:"4vw"},cartButton:{textAlign:"end",marginBottom:"2vh"},content:{height:"65vh",overflow:"auto",display:"flex"}})((function(e){var t=e.classes,n=Object(p.useSelector)((function(e){return e.products.listProducts})),r=Object(p.useSelector)((function(e){return e.products.instance})),a=c.a.useState(!1),o=Object(m.a)(a,2),s=o[0],i=o[1],u=Object(p.useDispatch)();return c.a.createElement("div",null,c.a.createElement(xe,{open:s,onClose:function(){return i(!1)}}),c.a.createElement("div",{className:t.cartButton},c.a.createElement(z.a,{variant:"contained",color:"primary",onClick:function(){return i(!0)}},"Cart ",c.a.createElement(ue.a,{className:"icon-ecommerce"})," (",r.products.length,")")),c.a.createElement("div",{className:t.content},n.map((function(e,n){return c.a.createElement($.a,{key:e.productName+n,className:t.card},c.a.createElement(te.a,null,c.a.createElement(se.a,{className:t.media,image:"https://cuoredimamma.pt/wp-content/uploads/2018/11/blog-ph.jpg",title:"Product"}),c.a.createElement(ce.a,null,c.a.createElement(Q.a,{gutterBottom:!0,variant:"h5",component:"h2"},e.productName),c.a.createElement(Q.a,{component:"p"},e.productPrice,"\u20ac"))),c.a.createElement(re.a,null,c.a.createElement(z.a,{size:"small",color:"primary",onClick:function(){return u((t=r.jwcontext.id,n=e.productId,function(){var e=Object(b.a)(E.a.mark((function e(r){var a,c;return E.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,I(t,n);case 3:a=e.sent,c=a.data,r({type:"SELECT_PRODUCTS",instance:c}),e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log(e.t0.message);case 11:case"end":return e.stop()}}),e,null,[[0,8]])})));return function(t){return e.apply(this,arguments)}}()));var t,n}},"Add to Cart")))}))))}));function xe(e){var t=e.onClose,n=Object(X.a)(e,ge),r=Object(p.useSelector)((function(e){return e.products.instance}));return c.a.createElement(pe.a,Object.assign({onClose:function(){return t()},"aria-labelledby":"simple-dialog-title"},n),c.a.createElement(de.a,{id:"simple-dialog-title"},"Shopping Cart"),c.a.createElement("div",null,c.a.createElement(ve.a,null,r&&r.products&&r.products.map((function(e,t){return c.a.createElement(be.a,{key:e.productName+" "+t},c.a.createElement(Oe.a,{primary:e.productName}),c.a.createElement(Oe.a,{primary:e.productPrice}))})))))}var ye=n(114),Se=n.n(ye),Ce=n(183),Te=n.n(Ce),_e=n(184),we=n.n(_e);var Re=Object(q.withStyles)((function(){return{select:{marginTop:"5vh"}}}))((function(e){var t=e.classes,n=e.setDeliveryOption,r=e.setAddress,a=e.setName,o=e.name,s=e.address,i=e.deliveryOption,u=Object(p.useSelector)((function(e){return e.products.deliveryOptions}));return c.a.createElement("div",null,c.a.createElement(Se.a,{id:"Client Name",fullWidth:!0,label:"Name",value:o,onChange:function(e){return a(e.target.value)},margin:"normal"}),c.a.createElement("br",null),c.a.createElement(Se.a,{id:"Address",fullWidth:!0,label:"Address",value:s,onChange:function(e){return r(e.target.value)},margin:"normal"}),c.a.createElement("br",null),c.a.createElement("div",{className:t.select},c.a.createElement(Q.a,null,"Delivery Option:"),c.a.createElement("br",null),c.a.createElement(Te.a,{value:i,onChange:function(e){return n(e.target.value)},inputProps:{name:"delivery-options",id:"delivert-options-simple"}},u.map((function(e,t){return c.a.createElement(we.a,{value:e.name,key:e.name+t},e.name)})))))}));var Ue=Object(q.withStyles)((function(){return{userInformation:{marginTop:"2vh",marginBottom:"2vh"},productInformation:{marginTop:"2vh",marginBottom:"2vh"},productSeparation:{marginBottom:"1vh"},price:{marginLeft:"85vw",marginTop:"40vh"}}}))((function(e){var t=e.classes,n=Object(p.useSelector)((function(e){return e.products.instance}));return c.a.createElement("div",null,c.a.createElement("div",{className:t.userInformation},c.a.createElement(Q.a,{variant:"h2"},"Customer Information:"),c.a.createElement("div",null,c.a.createElement(Q.a,null,"Name: ",n.customer.name),c.a.createElement(Q.a,null,"Address: ",n.customer.address))),c.a.createElement("div",{className:t.productInformation},c.a.createElement(Q.a,{variant:"h2"},"Products:"),n.products.map((function(e,n){return c.a.createElement("div",{className:t.productSeparation,key:e.productName+n},c.a.createElement(Q.a,null,"Product Name: ",e.productName),c.a.createElement(Q.a,null,"Price: ",e.productPrice,"\u20ac"))}))),c.a.createElement("div",{className:t.price},c.a.createElement(Q.a,{variant:"h2"},"Total Price: ",n.products.reduce((function(e,t){return e+t.productPrice}),0),"\u20ac")))}));function Pe(){var e=Object(p.useSelector)((function(e){return e.products.instance}));return c.a.createElement(c.a.Fragment,null,c.a.createElement("div",null,c.a.createElement(Q.a,{variant:"h1"},"Order:"),"Address: ",e.order.address," ",c.a.createElement("br",null),"Delivery: ",e.order.delivery," ",c.a.createElement("br",null),"Id: ",e.order.id),c.a.createElement("div",null,c.a.createElement(Q.a,{variant:"h1"},"Products:"),e.order.products.map((function(e,t){return c.a.createElement("div",{key:e.productName+t},"Name: ",e.productName," ",c.a.createElement("br",null),"Price: ",e.productPrice," ",c.a.createElement("br",null))}))))}function Ie(){var e=Object(p.useSelector)((function(e){return{RealTime:e.journey.services.RealTime,instance:e.products.instance}})),t=e.RealTime,n=e.instance,r=c.a.useState(new t),a=Object(m.a)(r,2),o=a[0],s=(a[1],Object(p.useDispatch)());function i(e){console.log("A connection has been established"),console.log(e)}function u(e){console.log("A message has been sent by the server");var t=Object.assign({},{},n),r=JSON.parse(e.data);r.state&&r.status&&r.updatedBy&&(t.jwcontext.state=r.state,t.jwcontext.status=r.status,s(L(t))),console.log(e)}function l(e){console.log("An error message has been received"),console.log(e)}function d(e){console.log("The Connection has been closed"),console.log(e)}return c.a.useEffect((function(){o.addEvent("onopen",i),o.addEvent("onmessage",u),o.addEvent("onerror",l),o.addEvent("onclose",d),o.connect("localhost:4502/ordermanagement/v1/websocket/"+n.jwcontext.id)}),[]),c.a.createElement("div",null,"...Waiting for the store")}var Ne=Object(q.withStyles)((function(e){return{root:{width:"94vw",marginLeft:"3vw",marginRight:"3vw",marginTop:"1vh",marginBottom:"1vh",height:"85vh"},navigationButtons:{position:"absolute",right:"5vw",bottom:"5vh"}}}))((function(e){var t=e.classes,n=Object(p.useDispatch)(),r=Object(p.useSelector)((function(e){return{instance:e.products.instance,preferences:e.products.preferences,defaultDeliveryOptions:e.products.deliveryOptions,UserPreferences:e.journey.services.UserPreferences,User:e.journey.services.User}})),a=r.instance,o=r.UserPreferences,s=r.User,i=r.preferences,u=r.defaultDeliveryOptions,l=c.a.useState(0),d=Object(m.a)(l,2),f=d[0],v=d[1],h=c.a.useState(""),O=Object(m.a)(h,2),g=O[0],j=O[1],x=c.a.useState(""),y=Object(m.a)(x,2),S=y[0],C=y[1],T=c.a.useState(u[0].name),_=Object(m.a)(T,2),w=_[0],R=_[1],U=[{name:"New Order",component:c.a.createElement(je,null)},{name:"Customer Information",component:c.a.createElement(Re,{setName:j,setAddress:C,setDeliveryOption:R,name:g,address:S,deliveryOption:w})},{name:"Submit Order",component:c.a.createElement(Ue,null)},{name:"Waiting for Store",component:c.a.createElement(Ie,null)},{name:"Finish",component:c.a.createElement(Pe,null)}];return c.a.useEffect((function(){v("PRODUCT CHOSEN"===a.jwcontext.status?0:"SUBMIT ORDER"!==a.jwcontext.status||a.customer?"SUBMIT ORDER"===a.jwcontext.status?2:"WAITING FOR \nORDER IN STORE"===a.jwcontext.status?3:"END"===a.jwcontext.status?4:void 0:1)}),[a]),c.a.createElement("div",{className:t.root},c.a.createElement(F.a,{activeStep:f},U.map((function(e){return c.a.createElement(W.a,{key:e.name},c.a.createElement(V.a,null,e.name))}))),c.a.createElement("div",null,f===U.length?c.a.createElement("div",null,c.a.createElement(Q.a,null,"All steps completed - you're finished"),c.a.createElement(z.a,{onClick:this.handleReset},"New Order")):c.a.createElement("div",null,U[f].component,c.a.createElement("div",{className:t.navigationButtons},3!==f&&c.a.createElement(z.a,{disabled:function(e){switch(e){case 0:return!(a.products.length>0);case 1:case 2:case 3:case 4:return!1;default:return}}(f),variant:"contained",color:"primary",onClick:function(){var e;"PRODUCT CHOSEN"===a.jwcontext.status?n((e=a.jwcontext.id,function(){var t=Object(b.a)(E.a.mark((function t(n){var r,a;return E.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,N(e);case 3:r=t.sent,a=r.data,n({type:"CREATE_ORDER",instance:a}),t.next=11;break;case 8:t.prev=8,t.t0=t.catch(0),console.log(t.t0.message);case 11:case"end":return t.stop()}}),t,null,[[0,8]])})));return function(e){return t.apply(this,arguments)}}())):"SUBMIT ORDER"!==a.jwcontext.status||a.customer?"SUBMIT ORDER"===a.jwcontext.status?n(function(e){return function(){var t=Object(b.a)(E.a.mark((function t(n){var r,a;return E.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,k(e);case 3:r=t.sent,a=r.data,n({type:"SUBMIT_ORDER",instance:a}),t.next=11;break;case 8:t.prev=8,t.t0=t.catch(0),console.log(t.t0.message);case 11:case"end":return t.stop()}}),t,null,[[0,8]])})));return function(e){return t.apply(this,arguments)}}()}(a.jwcontext.id)):"END"===a.jwcontext.status&&s.requestUser().then((function(e){o.deleteUserPreference(e.id,i.id).then((function(){window.location.reload()}))})):n(function(e,t){return function(){var n=Object(b.a)(E.a.mark((function n(r){var a,c;return E.a.wrap((function(n){for(;;)switch(n.prev=n.next){case 0:return n.prev=0,n.next=3,D(e,t);case 3:a=n.sent,c=a.data,r({type:"UPDATE_CUSTOMER_INFO",instance:c}),n.next=11;break;case 8:n.prev=8,n.t0=n.catch(0),console.log(n.t0.message);case 11:case"end":return n.stop()}}),n,null,[[0,8]])})));return function(e){return n.apply(this,arguments)}}()}(a.jwcontext.id,{customer:{name:g,address:S},deliveryOption:w}))}},function(e){switch(e){case 0:return"Create Order";case 1:return"Submit Customer Information";case 2:return"Submit Order";case 3:return;case 4:return"Create a new order";default:return}}(f))))))}));n(377);function De(){return c.a.createElement("div",{class:"loadingbase"},c.a.createElement("div",{class:"preloader"},c.a.createElement("div",{class:"box"},c.a.createElement("div",{class:"box__inner"},c.a.createElement("div",{class:"box__back-flap"}),c.a.createElement("div",{class:"box__right-flap"}),c.a.createElement("div",{class:"box__front-flap"}),c.a.createElement("div",{class:"box__left-flap"}),c.a.createElement("div",{class:"box__front"}))),c.a.createElement("div",{class:"box"},c.a.createElement("div",{class:"box__inner"},c.a.createElement("div",{class:"box__back-flap"}),c.a.createElement("div",{class:"box__right-flap"}),c.a.createElement("div",{class:"box__front-flap"}),c.a.createElement("div",{class:"box__left-flap"}),c.a.createElement("div",{class:"box__front"}))),c.a.createElement("div",{class:"line"},c.a.createElement("div",{class:"line__inner"})),c.a.createElement("div",{class:"line"},c.a.createElement("div",{class:"line__inner"})),c.a.createElement("div",{class:"line"},c.a.createElement("div",{class:"line__inner"})),c.a.createElement("div",{class:"line"},c.a.createElement("div",{class:"line__inner"})),c.a.createElement("div",{class:"line"},c.a.createElement("div",{class:"line__inner"})),c.a.createElement("div",{class:"line"},c.a.createElement("div",{class:"line__inner"})),c.a.createElement("div",{class:"line"},c.a.createElement("div",{class:"line__inner"}))))}var ke=Object(q.withStyles)((function(e){return{}}))((function(e){e.classes;var t=c.a.useState(!1),n=Object(m.a)(t,2),r=n[0],a=n[1],o=Object(p.useSelector)((function(e){return e.products.instance})),s=Object(p.useSelector)((function(e){return e.products.preferences})),i=Object(p.useSelector)((function(e){return e.journey.services.UserPreferences})),u=Object(p.useSelector)((function(e){return e.journey.services.User}));return c.a.useEffect((function(){u.requestUser().then((function(e){s.properties.length<1?i.createProperty(e.id,s.id,{name:"instanceId",description:"instanceId of ordermanagement",value:o.jwcontext.id}).then((function(){a(!0)})):a(!0),console.log(o),console.log(s)}))}),[]),c.a.createElement("div",null,r?c.a.createElement(Ne,null):c.a.createElement(De,null))})),Le=n(185),Ae=n.n(Le),Be=Object(f.withRootHoc)((function(){var e=c.a.useState(!1),t=Object(m.a)(e,2),n=t[0],r=t[1],a=Object(p.useSelector)((function(e){return{preferences:e.products.preferences,UserPreferences:e.journey.services.UserPreferences,User:e.journey.services.User,dependencies:e.products.dependencies}})),o=a.preferences,s=a.User,i=a.UserPreferences,u=a.dependencies,l=Object(p.useDispatch)();return c.a.useState((function(){l(B())})),c.a.useEffect((function(){l(function(){var e=Object(b.a)(E.a.mark((function e(t){var n,r;return E.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,w();case 3:n=e.sent,r=n.data,t({type:"GET_PRODUCTS",products:r}),e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log(e.t0.message);case 11:case"end":return e.stop()}}),e,null,[[0,8]])})));return function(t){return e.apply(this,arguments)}}()),l(function(){var e=Object(b.a)(E.a.mark((function e(t){var n,r;return E.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,R();case 3:n=e.sent,r=n.data,t({type:"GET_CATEGORIES",categories:r}),e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log(e.t0.message);case 11:case"end":return e.stop()}}),e,null,[[0,8]])})));return function(t){return e.apply(this,arguments)}}()),l(function(){var e=Object(b.a)(E.a.mark((function e(t){var n,r;return E.a.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.prev=0,e.next=3,U();case 3:n=e.sent,r=n.data,t({type:"GET_DELIVERY_OPTIONS",deliveryOptions:r}),e.next=11;break;case 8:e.prev=8,e.t0=e.catch(0),console.log(e.t0.message);case 11:case"end":return e.stop()}}),e,null,[[0,8]])})));return function(t){return e.apply(this,arguments)}}())}),[]),c.a.useEffect((function(){if(!o){var e=Ae.a.stringify({expression:"path==*ordermanagement*",limit:1,sort:"path;asc"},{arrayFormat:"repeat"});s.requestUser().then((function(t){i.queryUserPreferences(t.id,e).then((function(e){e&&e.data&&e.data.length<1?i.createPreference(t.id,{path:"ordermanagement"}).then((function(e){l(M()),l(A(e.data))})):(l(A(e.data[0])),e&&e.data&&e.data[0].properties&&e.data[0].properties.length>0?(l({type:"SET_INSTANCE_ID",instanceId:e.data[0].properties[0].value}),l(function(e){return function(){var t=Object(b.a)(E.a.mark((function t(n){var r,a;return E.a.wrap((function(t){for(;;)switch(t.prev=t.next){case 0:return t.prev=0,t.next=3,P(e);case 3:r=t.sent,a=r.data,n(L(a)),t.next=11;break;case 8:t.prev=8,t.t0=t.catch(0),console.log(t.t0.message);case 11:case"end":return t.stop()}}),t,null,[[0,8]])})));return function(e){return t.apply(this,arguments)}}()}(e.data[0].properties[0].value))):l(M()))})).catch((function(e){console.log(e)}))}))}}),[o]),c.a.useEffect((function(){u.fetchedListProducts&&u.fetchedCategories&&u.fetchedDeliveryOptions&&u.fetchedInstance&&u.fetchedPreferences&&r(!0)}),[u]),c.a.createElement("div",null,n?c.a.createElement(ke,null):c.a.createElement(De,null))})),Me=n(115),qe=n(4),Ge={listProducts:[],categories:"",instance:{},deliveryOptions:[],instanceId:void 0,preferences:void 0,dependencies:{fetchedListProducts:!1,fetchedCategories:!1,fetchedInitialInstanceId:!1,fetchedDeliveryOptions:!1,fetchedInstance:!1,fetchedPreferences:!1}},Fe={products:function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:Ge,t=arguments.length>1?arguments[1]:void 0;switch(t.type){case"SET_INSTANCE_ID":return Object(qe.a)(Object(qe.a)({},e),{},{instanceId:t.instanceId,dependencies:Object(qe.a)(Object(qe.a)({},e.dependencies),{},{fetchedInitialInstanceId:!0})});case"CREATE_INSTANCE":return Object(qe.a)(Object(qe.a)({},e),{},{instance:t.instance,dependencies:Object(qe.a)(Object(qe.a)({},e.dependencies),{},{fetchedInstance:!0})});case"CREATE_ORDER":return Object(qe.a)(Object(qe.a)({},e),{},{instance:t.instance});case"GET_CATEGORIES":return Object(qe.a)(Object(qe.a)({},e),{},{categories:t.categories,dependencies:Object(qe.a)(Object(qe.a)({},e.dependencies),{},{fetchedCategories:!0})});case"GET_DELIVERY_OPTIONS":return Object(qe.a)(Object(qe.a)({},e),{},{deliveryOptions:t.deliveryOptions,dependencies:Object(qe.a)(Object(qe.a)({},e.dependencies),{},{fetchedDeliveryOptions:!0})});case"GET_PRODUCTS":return Object(qe.a)(Object(qe.a)({},e),{},{listProducts:t.products,dependencies:Object(qe.a)(Object(qe.a)({},e.dependencies),{},{fetchedListProducts:!0})});case"READ_UPDATE_PROCESS":return Object(qe.a)(Object(qe.a)({},e),{},{instance:t.instance,dependencies:Object(qe.a)(Object(qe.a)({},e.dependencies),{},{fetchedInstance:!0})});case"SELECT_PRODUCTS":case"SUBMIT_ORDER":case"UPDATE_CUSTOMER_INFO":return Object(qe.a)(Object(qe.a)({},e),{},{instance:t.instance});case"SET_PREFERENCES":return Object(qe.a)(Object(qe.a)({},e),{},{preferences:t.preferences,dependencies:Object(qe.a)(Object(qe.a)({},e.dependencies),{},{fetchedPreferences:!0})});case"RESET":return Ge;default:return e}}},Je=Object(f.createStoreSingleton)(Fe),We=Object(f.FinalJourney)(Be,Je,Me.a,Me.b),He=function(e){Object(l.a)(n,e);var t=Object(d.a)(n);function n(){return Object(i.a)(this,n),t.apply(this,arguments)}return Object(u.a)(n,[{key:"handleReceiveMessage",value:function(){}},{key:"render",value:function(){return c.a.createElement(p.Provider,{store:Je},c.a.createElement("div",null,c.a.createElement(We,{handleReceiveMessage:this.handleReceiveMessage})))}}]),n}(c.a.Component);s.a.render(c.a.createElement(He,null),document.getElementById("root"))},53:function(e,t){e.exports=OmniJourney},54:function(e,t){e.exports=UiLibCore.Button}},[[189,1,2]]]);
//# sourceMappingURL=main.8eaeab68.chunk.js.map