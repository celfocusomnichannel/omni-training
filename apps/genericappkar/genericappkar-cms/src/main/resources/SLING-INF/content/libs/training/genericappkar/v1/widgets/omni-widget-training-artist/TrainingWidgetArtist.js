!function(e,t){"object"==typeof exports&&"object"==typeof module?module.exports=t(require("React"),require("OmniWidget"),require("ReactRedux")):"function"==typeof define&&define.amd?define(["React","OmniWidget","ReactRedux"],t):"object"==typeof exports?exports.TrainingWidgetArtist=t(require("React"),require("OmniWidget"),require("ReactRedux")):e.TrainingWidgetArtist=t(e.React,e.OmniWidget,e.ReactRedux)}(window,(function(e,t,r){return function(e){var t={};function r(n){if(t[n])return t[n].exports;var o=t[n]={i:n,l:!1,exports:{}};return e[n].call(o.exports,o,o.exports,r),o.l=!0,o.exports}return r.m=e,r.c=t,r.d=function(e,t,n){r.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},r.r=function(e){"undefined"!=typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},r.t=function(e,t){if(1&t&&(e=r(e)),8&t)return e;if(4&t&&"object"==typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(r.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var o in e)r.d(n,o,function(t){return e[t]}.bind(null,o));return n},r.n=function(e){var t=e&&e.__esModule?function(){return e.default}:function(){return e};return r.d(t,"a",t),t},r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},r.p="",r(r.s=25)}([function(t,r){t.exports=e},function(e,t,r){e.exports=r(23)()},function(e,r){e.exports=t},function(e,t){e.exports=UiLibCore.Typography},function(e,t,r){var n=r(18),o=r(19),a=r(20),i=r(22);e.exports=function(e,t){return n(e)||o(e,t)||a(e,t)||i()},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){e.exports=r},function(e,t){e.exports=UiLibCore.styles},function(e,t){function r(t){return e.exports=r=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)},e.exports.default=e.exports,e.exports.__esModule=!0,r(t)}e.exports=r,e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){e.exports=UiLibCore.TextField},function(e,t){e.exports=function(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){function r(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}e.exports=function(e,t,n){return t&&r(e.prototype,t),n&&r(e,n),e},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t,r){var n=r(15);e.exports=function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&n(e,t)},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t,r){var n=r(16).default,o=r(17);e.exports=function(e,t){if(t&&("object"===n(t)||"function"==typeof t))return t;if(void 0!==t)throw new TypeError("Derived constructors may only return object or undefined");return o(e)},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){e.exports=UiLibCore.Button},function(e,t){function r(){return e.exports=r=Object.assign||function(e){for(var t=1;t<arguments.length;t++){var r=arguments[t];for(var n in r)Object.prototype.hasOwnProperty.call(r,n)&&(e[n]=r[n])}return e},e.exports.default=e.exports,e.exports.__esModule=!0,r.apply(this,arguments)}e.exports=r,e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){function r(t,n){return e.exports=r=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e},e.exports.default=e.exports,e.exports.__esModule=!0,r(t,n)}e.exports=r,e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){function r(t){return"function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?(e.exports=r=function(e){return typeof e},e.exports.default=e.exports,e.exports.__esModule=!0):(e.exports=r=function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e},e.exports.default=e.exports,e.exports.__esModule=!0),r(t)}e.exports=r,e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){e.exports=function(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){e.exports=function(e){if(Array.isArray(e))return e},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){e.exports=function(e,t){var r=null==e?null:"undefined"!=typeof Symbol&&e[Symbol.iterator]||e["@@iterator"];if(null!=r){var n,o,a=[],i=!0,u=!1;try{for(r=r.call(e);!(i=(n=r.next()).done)&&(a.push(n.value),!t||a.length!==t);i=!0);}catch(e){u=!0,o=e}finally{try{i||null==r.return||r.return()}finally{if(u)throw o}}return a}},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t,r){var n=r(21);e.exports=function(e,t){if(e){if("string"==typeof e)return n(e,t);var r=Object.prototype.toString.call(e).slice(8,-1);return"Object"===r&&e.constructor&&(r=e.constructor.name),"Map"===r||"Set"===r?Array.from(e):"Arguments"===r||/^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(r)?n(e,t):void 0}},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){e.exports=function(e,t){(null==t||t>e.length)&&(t=e.length);for(var r=0,n=new Array(t);r<t;r++)n[r]=e[r];return n},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t){e.exports=function(){throw new TypeError("Invalid attempt to destructure non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")},e.exports.default=e.exports,e.exports.__esModule=!0},function(e,t,r){"use strict";var n=r(24);function o(){}function a(){}a.resetWarningCache=o,e.exports=function(){function e(e,t,r,o,a,i){if(i!==n){var u=new Error("Calling PropTypes validators directly is not supported by the `prop-types` package. Use PropTypes.checkPropTypes() to call them. Read more at http://fb.me/use-check-prop-types");throw u.name="Invariant Violation",u}}function t(){return e}e.isRequired=e;var r={array:e,bool:e,func:e,number:e,object:e,string:e,symbol:e,any:e,arrayOf:t,element:e,elementType:e,instanceOf:t,node:e,objectOf:t,oneOf:t,oneOfType:t,shape:t,exact:t,checkPropTypes:a,resetWarningCache:o};return r.PropTypes=r,r}},function(e,t,r){"use strict";e.exports="SECRET_DO_NOT_PASS_THIS_OR_YOU_WILL_BE_FIRED"},function(e,t,r){"use strict";r.r(t),r.d(t,"TrainingWidgetArtist",(function(){return U}));var n=r(9),o=r.n(n),a=r(10),i=r.n(a),u=r(11),s=r.n(u),c=r(12),l=r.n(c),p=r(7),f=r.n(p),d=r(0),x=r.n(d),m=r(5),y=r(2),b=r(4),v=r.n(b),h=r(6);var g=r(3),_=r.n(g),O=r(8),w=r.n(O),j=r(13),S=r.n(j),E=r(1),R=r.n(E);var k=function(e){var t=e.artistInfo,r=e.classes;return x.a.createElement("div",{className:r.artistInfoRoot},x.a.createElement("img",{className:r.artWork,src:t.artworkUrl100}),x.a.createElement("div",{className:r.trackInfo},x.a.createElement(_.a,{variant:"subtitle2",component:"span",classes:{root:r.artistName}},"".concat(t.trackName," by ").concat(t.artistName)),x.a.createElement(_.a,{variant:"subtitle2",component:"span",classes:{root:r.collectionName}},"".concat(t.collectionName," | ").concat(t.primaryGenreName," | ").concat(t.country))))};k.propTypes={artistInfo:R.a.shape({trackName:R.a.string,artistName:R.a.number,collectionName:R.a.number,primaryGenreName:R.a.number,artworkUrl100:R.a.string,country:R.a.string}),classes:R.a.object};var M=Object(h.withStyles)((function(e){return{artistInfoRoot:{padding:"10px",display:"flex",border:"solid 1px ".concat(e.palette.primary.main),position:"relative",height:"auto",width:"auto",opacity:"0.8",backgroundColor:"dark"===e.palette.type?e.palette.background.default:e.palette.text.primary,color:e.palette.common.white},artWork:{width:100,height:"auto"},trackInfo:{display:"flex",flexDirection:"column",marginLeft:15},artistName:{fontSize:"1.5rem"},collectionName:{fontSize:"1rem",marginTop:"5px"}}}))(x.a.memo(k));var T=Object(y.withRootHoc)(Object(h.withStyles)((function(e){return{widgetRoot:{display:"flex",height:"100%",alignItems:"start",justifyContent:"flex-start",position:"relative",flexDirection:"column",padding:10,backgroundColor:e.palette.background.default},filterRoot:{display:"flex",width:"100%",marginBottom:10,alignItems:"flex-end"},inputField:{display:"block",position:"relative",backgroundColor:"inherit",width:"100%",margin:10,padding:0},artistInfoContainer:{width:"100%",height:"calc(100% - 170px)"}}}))((function(e){var t=e.classes,r=Object(m.useSelector)((function(e){return{I18nProvider:e.widget.services.I18nProvider,HttpClient:e.widget.services.HttpClient}})),n=r.I18nProvider,o=r.HttpClient,a=x.a.useState("Taylor Swift"),i=v()(a,2),u=i[0],s=i[1],c=x.a.useState(20),l=v()(c,2),p=l[0],f=l[1],d=x.a.useState([]),y=v()(d,2),b=y[0],h=y[1],g=x.a.useState(!1),O=v()(g,2),j=O[0],E=O[1],R=x.a.useCallback((function(){E(!0),function(e,t){var r=arguments.length>2&&void 0!==arguments[2]?arguments[2]:2,n={method:"get",url:"/bin/mvc.do/genericappkar/v1/app/search?artistName="+t+"&limit="+r};return new Promise((function(t,r){e.request(n).then((function(e){var n=e.data;console.log(n,"response"),n?t(n):r("not found")})).catch((function(e){console.log(e,"error"),r(e)}))}))}(o,u,p).then((function(){var e=arguments.length>0&&void 0!==arguments[0]?arguments[0]:[];h(e),E(!1)})).catch((function(){E(!1)}))}),[u,p,o]);return x.a.useEffect((function(){R()}),[]),x.a.createElement("div",{className:t.widgetRoot},x.a.createElement(_.a,{variant:"h2"},n.Labeli18n("search_music_artist")),x.a.createElement("div",{className:t.filterRoot},x.a.createElement(w.a,{fullWidth:!0,className:t.inputField,id:"search-artist",label:n.Labeli18n("enter_music_artist"),value:u,onChange:function(e){s(e.target.value)}}),x.a.createElement(w.a,{fullWidth:!0,className:t.inputField,id:"search-limit",label:n.Labeli18n("result_count"),value:p,onChange:function(e){f(e.target.value)}}),x.a.createElement(S.a,{variant:"outlined",color:"primary",onClick:R},n.Labeli18n("search"))),!j&&b&&b.length>0?x.a.createElement("div",{className:t.artistInfoContainer},b.map((function(e){return x.a.createElement("a",{href:e.trackViewUrl,target:"_blank",style:{textDecoration:"none"}},x.a.createElement(M,{artistInfo:e}))}))):j?n.Labeli18n("loading"):n.Labeli18n("artist_not_found"))}))),C=r(14),P=r.n(C);var N={example:""};function I(e){var t=function(){if("undefined"==typeof Reflect||!Reflect.construct)return!1;if(Reflect.construct.sham)return!1;if("function"==typeof Proxy)return!0;try{return Boolean.prototype.valueOf.call(Reflect.construct(Boolean,[],(function(){}))),!0}catch(e){return!1}}();return function(){var r,n=f()(e);if(t){var o=f()(this).constructor;r=Reflect.construct(n,arguments,o)}else r=n.apply(this,arguments);return l()(this,r)}}var W,L={reducerOne:function(e,t){if(void 0===e)return N;switch(t.type){case"SET_EXAMPLE":return P()({},e,{example:t.example});default:return e}}},A=Object(y.createStoreSingleton)(L),U=function(e){s()(r,e);var t=I(r);function r(e){var n;return o()(this,r),n=t.call(this,e),W=Object(y.FinalWidget)(T,A,"omni-widget-training-artist","genericappkar","v1",e),n}return i()(r,[{key:"render",value:function(){return x.a.createElement(m.Provider,{store:A},x.a.createElement(W,this.props))}}]),r}(x.a.Component)}])}));
//# sourceMappingURL=TrainingWidgetArtist.js.map