module.exports = {
    webpack: {
        configure: (webpackConfig) => {
            webpackConfig.externals = [
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
                { 'omni-journey': 'OmniJourney' },
                { 'react-redux': 'ReactRedux' },
                { redux: 'Redux' },
                { react: 'React' },
                { 'react-dom': 'ReactDOM' }
            ];
            return webpackConfig;
        }
    }
};
