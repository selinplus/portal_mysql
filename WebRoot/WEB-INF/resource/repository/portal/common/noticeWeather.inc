if(typeof(weathers)!='undefined' ){
var ss=weathers;
document.writeln('<ul class="weather">');
for(var i=0;i<ss.length;i++){
var localWeather = weathers[i];
var url=baseUrlPath+'/portal/weather/weatherPage?id='+localWeather.url;
document.writeln('       <li>');
document.writeln('           <span>');
document.writeln('           <p><a href="'+url+'" target=_blank><img src="'+baseUrlPath+'/res/repository/portal/images/'+localWeather.img+'" alt=""/></a></p>');
document.writeln('           <p class="white">'+localWeather.week+'</p>');
document.writeln('           </span>');
if(i!=2)
document.writeln('           <span class="sx1"> </span>');
document.writeln('       </li>');
};

document.writeln('</ul>');
}