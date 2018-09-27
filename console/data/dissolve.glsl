#ifdef GL_ES
precision mediump float;
precision mediump int;
#endif

#define PROCESSING_TEXTURE_SHADER

uniform sampler2D texture;
uniform float intens;
uniform float randx;
uniform float randy;

varying vec4 vertTexCoord;

float rand(vec2 co)
{
    float a = 12.9898;
    float b = 78.233;
    float c = 43758.5453;
    float dt= dot(co.xy ,vec2(a,b));
    float sn= mod(dt,3.14);
    return fract(sin(sn) * c)*2.-1.;
}

void main(void) {
  
  vec2 tc = vertTexCoord.st + vec2(rand(vec2(-vertTexCoord.x, -vertTexCoord.y))*randx, rand(vec2(vertTexCoord.x, vertTexCoord.y))*randy);
  
  vec4 col = texture2D(texture, tc);
             
  gl_FragColor = vec4(col.rgb, 1.0) * intens;  
}