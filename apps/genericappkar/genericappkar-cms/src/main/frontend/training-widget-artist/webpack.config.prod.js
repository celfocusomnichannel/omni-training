var path = require('path');

module.exports = {
    mode: 'production',
    entry: './src/index.js',
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /(node_modules|bower_components)/,
                loader: 'babel-loader',
                options: {
                    presets: ['@babel/preset-env', '@babel/react'],
                    plugins: [
                        ['@babel/plugin-proposal-class-properties', { loose: true }],
                        ['@babel/plugin-proposal-object-rest-spread', { loose: true }],
                        '@babel/plugin-transform-runtime',
                        // for IE 11 support
                        '@babel/plugin-transform-object-assign'
                    ]
                }
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    },
    devtool: 'source-map',
    externals: [
        function (context, request, callback) {
            if (/@ui-lib\/core\/.*/.test(request)) {
                if (request.replace('@ui-lib/core', '') === '') {
                    return callback(null, 'var ' + 'UiLibCore');
                } else {
                    return callback(null, 'var ' + 'UiLibCore.' + request.replace('@ui-lib/core/', '').replace('/', '.'));
                }
            }
            if (/@ui-lib\/custom-components\/.*/.test(request)) {
                if (request.replace('@ui-lib/custom-components', '') === '') {
                    return callback(null, 'var ' + 'UiLibCustomComponents');
                } else {
                    return callback(null, 'var ' + 'UiLibCustomComponents.' + request.replace('@ui-lib/custom-components/', '').replace('/', '.'));
                }
            }
            callback();
        },
        { 'omni-widget': 'OmniWidget' },
        { 'react-redux': 'ReactRedux' },
        { redux: 'Redux' },
        { react: 'React' },
        { 'react-dom': 'ReactDOM' }
    ],
    output: {
        filename: 'TrainingWidgetArtist.js',
        library: 'TrainingWidgetArtist',
        libraryTarget: 'umd'
    }
};
