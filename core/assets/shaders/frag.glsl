#ifdef GL_ES
    precision mediump float;
#endif

varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform mat4 u_projTrans;
uniform vec2 u_mousePos;
uniform float u_radius;

void main() {
    vec4 color = texture2D(u_texture, v_texCoords).rgba;

    float distance = distance(gl_FragCoord, u_mousePos);

    if (distance < u_radius) {
        vec3 inverse = 1 - color.rgb;

        gl_FragColor = vec4(inverse, color.a);
    } else {
        gl_FragColor = color;
    }


}
